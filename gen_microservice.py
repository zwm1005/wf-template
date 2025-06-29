import os
import shutil
import sys

def print_help():
    print("""
用法示例（支持在 /wf-template 目录下执行）：
    python gen_microservice.py --project wf-order --package fun.werfamily.order --group fun.werfamily.trade --artifact wf-order 

参数说明：
    --project   新项目名（如 wf-order）
    --package   新包名（如 fun.werfamily.order）
    --group     新 groupId（如 fun.werfamily.trade）
    --artifact  新 artifactId（如 wf-order）
""")

def parse_args():
    arg_map = {
        '--project': None,
        '--package': None,
        '--group': None,
        '--artifact': None
    }
    args = sys.argv[1:]
    if "-h" in args or "--help" in args or len(args) == 0:
        print_help()
        sys.exit(0)
    for i in range(len(args)):
        if args[i] in arg_map and i + 1 < len(args):
            arg_map[args[i]] = args[i + 1]
    if not all(arg_map.values()):
        print("参数不完整！\n")
        print_help()
        sys.exit(1)
    return arg_map

def replace_in_file(file_path, replacements):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        for old, new in replacements.items():
            content = content.replace(old, new)
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(content)
    except Exception:
        pass

def batch_replace(root, replacements):
    for dirpath, dirnames, filenames in os.walk(root):
        for filename in filenames:
            file_path = os.path.join(dirpath, filename)
            replace_in_file(file_path, replacements)

def rename_all_modules(project_root, old_proj, new_proj):
    # 一级子模块重命名
    for name in os.listdir(project_root):
        path = os.path.join(project_root, name)
        if os.path.isdir(path) and name.startswith(old_proj):
            new_name = name.replace(old_proj, new_proj, 1)
            os.rename(path, os.path.join(project_root, new_name))
    # 二级子模块重命名
    for name in os.listdir(project_root):
        path = os.path.join(project_root, name)
        if os.path.isdir(path):
            for sub in os.listdir(path):
                sub_path = os.path.join(path, sub)
                if os.path.isdir(sub_path) and sub.startswith(old_proj):
                    new_sub = sub.replace(old_proj, new_proj, 1)
                    os.rename(sub_path, os.path.join(path, new_sub))

def move_package_dirs_recursively(root, old_pkg, new_pkg):
    """
    递归处理所有 src/main/java/ 和 src/test/java/ 下的包路径，将 old_pkg 物理目录替换为 new_pkg，并删除所有产生的空目录
    """
    old_path_parts = old_pkg.split('.')
    new_path_parts = new_pkg.split('.')
    for dirpath, dirnames, filenames in os.walk(root):
        if dirpath.endswith(os.path.join('src', 'main', 'java')) or dirpath.endswith(os.path.join('src', 'test', 'java')):
            abs_java_dir = dirpath
            old_full_path = os.path.join(abs_java_dir, *old_path_parts)
            new_full_path = os.path.join(abs_java_dir, *new_path_parts)
            if os.path.exists(old_full_path):
                os.makedirs(os.path.dirname(new_full_path), exist_ok=True)
                shutil.move(old_full_path, new_full_path)
                # 递归删除所有上层空目录（不删java目录本身）
                cleanup_empty_pkg_dirs_upward(abs_java_dir, old_full_path)

def cleanup_empty_pkg_dirs_upward(base_java_dir, start_path):
    """
    递归向上删除空目录，从 start_path 一直删到 base_java_dir 为止，不会删掉 base_java_dir 本身
    """
    current = start_path
    base_java_dir = os.path.abspath(base_java_dir)
    while True:
        parent = os.path.dirname(current)
        # stop if up to src/main/java 或 src/test/java 本身
        if os.path.abspath(current) == base_java_dir:
            break
        if os.path.isdir(current) and not os.listdir(current):
            os.rmdir(current)
            current = parent
        else:
            break

def delete_old_package_dirs_recursively(root, old_pkg):
    """
    删除所有模块下 src/main/java 和 src/test/java 下的 old_pkg 目录，并递归向上删除空父目录，直到java目录
    """
    old_path_parts = old_pkg.split('.')
    for dirpath, dirnames, filenames in os.walk(root):
        if dirpath.endswith(os.path.join('src', 'main', 'java')) or dirpath.endswith(os.path.join('src', 'test', 'java')):
            abs_java_dir = dirpath
            old_full_path = os.path.join(abs_java_dir, *old_path_parts)
            # 删除 /src/main/java/fun.werfamily.template 及其所有内容（如果存在）
            if os.path.exists(old_full_path):
                shutil.rmtree(old_full_path, ignore_errors=True)
                # 递归向上删除所有空的父目录，直到java_dir为止
                current = os.path.dirname(old_full_path)
                while os.path.abspath(current) != os.path.abspath(abs_java_dir):
                    if os.path.isdir(current) and not os.listdir(current):
                        os.rmdir(current)
                        current = os.path.dirname(current)
                    else:
                        break

def main():
    args = parse_args()
    new_project = args['--project']
    new_pkg = args['--package']
    new_group = args['--group']
    new_artifact = args['--artifact']

    # 脚本目录即模板目录
    script_dir = os.path.dirname(os.path.abspath(__file__))
    template_dir = script_dir
    old_pkg = "fun.werfamily.template"           # 需要与实际模板包路径对应
    old_project = "wf-template"
    old_group = "fun.werfamily.framework"
    old_artifact = "wf-template"

    # 目标项目路径：在脚本目录的上一级
    parent_dir = os.path.dirname(script_dir)
    target_project_dir = os.path.join(parent_dir, new_project)

    if not os.path.isdir(template_dir):
        print(f"模板目录 {template_dir} 不存在")
        sys.exit(1)

    if os.path.exists(target_project_dir):
        print(f"目标目录 {target_project_dir} 已存在，请先删除或更换项目名。")
        sys.exit(1)

    shutil.copytree(template_dir, target_project_dir, ignore=shutil.ignore_patterns('*.py', '__pycache__', '*.pyc', '.git', new_project))

    # 替换文本内容
    replacements = {
        old_pkg: new_pkg,
        old_project: new_project,
        old_group: new_group,
        old_artifact: new_artifact
    }
    batch_replace(target_project_dir, replacements)
    rename_all_modules(target_project_dir, old_project, new_project)
    move_package_dirs_recursively(target_project_dir, old_pkg, new_pkg)
    # 强力递归删除 /src/main/java/fun.werfamily.template 及其父目录（直到java）
    delete_old_package_dirs_recursively(target_project_dir, "fun.werfamily.template")
    # 删除新项目中的.git目录
    git_dir = os.path.join(target_project_dir, ".git")
    if os.path.exists(git_dir):
        shutil.rmtree(git_dir)
    print(f"\n新微服务骨架已生成: {target_project_dir}")
    print("请检查目录结构、pom和源码包结构是否符合预期。")

if __name__ == '__main__':
    main()