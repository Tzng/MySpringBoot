package team.myl.springboot.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import team.myl.springboot.util.bean.TreeNode;

/**
 * 构造目录JSON树 Created by fukang on 2017/5/26 0026.
 */
public class TreeBuilder {

	List<TreeNode> TreeNodes = new ArrayList<>();

	public String buildTree(List<TreeNode> TreeNodes) {

		TreeBuilder treeBuilder = new TreeBuilder(TreeNodes);

		return treeBuilder.buildJSONTree();
	}

	public TreeBuilder() {
	}

	public TreeBuilder(List<TreeNode> TreeNodes) {
		super();
		this.TreeNodes = TreeNodes;
	}

	// 构建JSON树形结构
	public String buildJSONTree() {
		List<TreeNode> TreeNodeTree = buildTree();
		return JSONArray.toJSONString(TreeNodeTree);
	}

	// 构建树形结构
	public List<TreeNode> buildTree() {
		List<TreeNode> treeTreeNodes = new ArrayList<>();
		List<TreeNode> rootTreeNodes = getRootTreeNodes();
		for (TreeNode rootTreeNode : rootTreeNodes) {
			try {
				buildChildTreeNodes(rootTreeNode);
				treeTreeNodes.add(rootTreeNode);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return treeTreeNodes;
	}

	// 递归子节点
	public void buildChildTreeNodes(TreeNode TreeNode) {
		List<TreeNode> children = getChildTreeNodes(TreeNode);
		if (!children.isEmpty()) {
			for (TreeNode child : children)
				try {
					buildChildTreeNodes(child);
				} catch (Exception e) {
					e.printStackTrace();
				}
			TreeNode.setChildren(children);
		}
	}

	// 获取父节点下所有的子节点
	public List<TreeNode> getChildTreeNodes(TreeNode pTreeNode) {
		List<TreeNode> childTreeNodes = new ArrayList<>();
		for (TreeNode n : TreeNodes) {
			if (pTreeNode.getId().equals(n.getPid())) {
				childTreeNodes.add(n);
			}
		}
		return childTreeNodes;
	}

	// 判断是否为根节点
	public boolean rootTreeNode(TreeNode TreeNode) {
		boolean isRootTreeNode = true;
		for (TreeNode n : TreeNodes) {
			if (TreeNode.getPid().equals(n.getId())) {
				isRootTreeNode = false;
				break;
			}
		}
		return isRootTreeNode;
	}

	// 获取集合中所有的根节点
	public List<TreeNode> getRootTreeNodes() {
		List<TreeNode> rootTreeNodes = new ArrayList<>();
		for (TreeNode n : TreeNodes) {
			if (rootTreeNode(n)) {
				rootTreeNodes.add(n);
			}
		}
		return rootTreeNodes;
	}
}