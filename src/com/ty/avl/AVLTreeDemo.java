package com.ty.avl;

/**
 * program : DataStructures
 * description : 平衡二叉树
 * author : jyf
 * date : 2020-08-28 14:23
 **/
public class AVLTreeDemo {

    public static void main(String[] args) {
        //  int[] arr = {4,3,6,5,7,8};
        //  int[] arr =  {10,12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9, 100, 200, 23, 26, 298, -10};
        AVLTree avlTree = new AVLTree();
        System.out.println();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }

        System.out.println("avl树的高度" + avlTree.root.height());
        System.out.println("avl树左子节点的高度" + avlTree.root.leftHeight());
        System.out.println("avl树右子节点的高度" + avlTree.root.rightHeight());
//        System.out.println(avlTree.root);
        avlTree.infixOrder();
    }
}

class AVLTree {
    Node root;

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            deleteNode(value);
        }
    }

    /**
     * 传入值删除节点
     *
     * @param value
     */
    public void deleteNode(int value) {
        // 首先去找到这个节点存在的地方
        Node targetNode = searchNode(value);
        // 如果这个节点是空, 那么我们没必要继续了
        if (targetNode == null) {
            return;
        }
        // 获取这个节点的父节点
        Node parent = searchParent(value);
        // 删除节点我们可以分三个情况
        if (targetNode.left == null && targetNode.right == null) {
            // 1. 他是一个叶子节点
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.right != null && parent.right.value == targetNode.value) {
                // 说明他在右子节点上
                parent.right = null;
            } else if (parent.left != null && parent.left.value == targetNode.value) {
                // 说明在左子节点上
                parent.left = null;
            }
        } else if (targetNode.left == null || targetNode.right == null) {
            // 2.他有一个子节点
            if (targetNode.right != null) {
                // 如果当前树的右节点不为空
                if (parent != null) {
                    // 那么我们判断当前节点是在父节点的左边还是右边
                    // 直接把父节点的子树指向targetNode树的右节点
                    if (parent.right != null && parent.right.value == targetNode.value) {
                        // 说明他在右子节点上
                        parent.right = targetNode.right;
                    } else if (parent.left != null && parent.left.value == targetNode.value) {
                        // 说明在左子节点上
                        parent.left = targetNode.right;
                    }
                } else {
                    // 如果父节点为空,我们直接把根节点指向右子节点
                    root = targetNode.right;
                }
            } else {
                // 那么我们判断当前节点是在父节点的左边还是右边
                // 直接把父节点的子树指向targetNode树的左节点
                if (parent != null) {
                    if (parent.right != null && parent.right.value == targetNode.value) {
                        // 说明他在右子节点上
                        parent.right = targetNode.left;
                    } else if (parent.left != null && parent.left.value == targetNode.value) {
                        // 说明在左子节点上
                        parent.left = targetNode.left;
                    }
                } else {
                    // 如果父节点为空,我们直接把根节点指向左子节点
                    root = targetNode.left;
                }
            }
        } else {
            // 3.他有两颗子树
            // 我们需要找到树下面比较合适的节点来替换当前树即可
            // 这个合适的节点就是右子树里面的最小值, 或者是左子树里面的最大值
            // 找到合适的节点以后删除他, 并且保存他的值,然后覆盖当前节点即可
            int leftMax = getRightMin(targetNode.right);
            targetNode.value = leftMax;
        }
    }


    /**
     * 查找当前节点父节点
     *
     * @param value 当前节点的value
     * @return 父节点
     */
    private Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    private Node searchNode(int value) {
        if (root == null) {
            return null;
        }
        return root.searchNode(value);
    }

    /**
     * 获取左子树的最大数
     *
     * @param node
     * @return
     */
    public int getLeftMax(Node node) {
        Node temp = node;
        if (temp.right == null) {
            // 如果当前这个节点已经是最大值, 那么我们直接删除并且返回即可
            deleteNode(temp.value);
            return temp.value;
        } else {
            return getLeftMax(temp.right);
        }
    }

    /**
     * 获取右子树的最大数
     *
     * @param node
     * @return
     */
    public int getRightMin(Node node) {
        Node temp = node;
        if (temp.left == null) {
            // 如果当前这个节点已经是最大值, 那么我们直接删除并且返回即可
            deleteNode(temp.value);
            return temp.value;
        } else {
            return getRightMin(temp.left);
        }
    }

    // 添加
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("树为空不能遍历");
        } else {
            root.infixOrder();
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    /**
     * 左旋的方法
     */
    private void leftRotate() {
        // 创建一个新的当前节点
        Node newNode = new Node(value);
        // 把新节点的左节点指向当前节点的左节点
        newNode.left = left;
        // 把新节点的右节点指向 当前节点右节点的左节点
        newNode.right = right.left;
        // 随后把当前节点替换成当前节点的右节点的
        this.value = right.value;
        // 然后把当前节点的左子节点, 指向新节点
        left = newNode;
        // 右子节点指向 原右子节点的右子节点
        right = right.right;
    }

    /**
     * 右旋的方法
     */
    private void rightRotate() {
        // 创建一个新的当前节点
        Node newNode = new Node(value);
        // 把新节点的右节点指向当前节点的右节点
        newNode.right = right;
        // 把新节点的左节点指向 当前节点左节点的右节点
        newNode.left = left.right;
        // 随后把当前节点替换成当前节点的左节点的的值
        this.value = left.value;
        // 然后把当前节点的右子节点, 指向新节点
        right = newNode;
        // 左子节点指向 原左子节点的左子节点
        left = left.left;
    }

    /**
     * 获取当前树的高度
     *
     * @return 返回高度
     */
    public int height() {
        // 这里的意思就是, 分别去求 左右两个子树的高度, +1 是精髓所在!! 返回的最后是当前这棵树的高度
        return Math.max((this.left == null ? 0 : this.left.height()), (this.right == null ? 0 : this.right.height())) + 1;
    }

    /**
     * 返回左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (left != null) {
            return left.height();
        } else {
            return 0;
        }
    }

    /**
     * 返回右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (right != null) {
            return right.height();
        } else {
            return 0;
        }
    }

    /**
     * 寻找节点
     *
     * @return
     */
    public Node searchNode(int value) {
        // 如果跟当前节点的value相等那就去找这个
        if (this.value == value) {
            return this;
        }

        if (this.left != null && this.value > value) {
            return this.left.searchNode(value);
        } else if (this.right != null && this.value <= value) {
            // 右边有就去右边找
            return this.right.searchNode(value);
        } else {
            // 说明都没有,直接返回null
            return null;
        }
    }

    /**
     * 找到这个节点的父节点
     *
     * @param value 需要找的节点
     * @return 父节点
     */
    public Node searchParent(int value) {
        // 如果左节点或者右节点的value 等于当前value那么当前节点即为父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            // 说明当前节点为父节点
            return this;
        }
        if (this.value > value) {
            // 如果当前的value大于传入的值, 说明我们要找的值在左边
            if (this.left != null) {
                return this.left.searchParent(value);
            }
        } else if (this.value <= value) {
            // 说明这个数大, 我们往右边去找
            if (this.right != null) {
                return this.right.searchParent(value);
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    // 添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            // 说明要向左挂
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 说明要向右挂
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 需要判断树是否是平衡二叉树
        if (rightHeight() - leftHeight() > 1) {
            // 说明需要左旋
            // 在左旋之前, 需要判断一下. 右子树的左子树是否大于右子树的右子树
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 这里需要先把右子树右旋, 然后在左旋
                right.rightHeight();
                // 随后左旋
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        if (leftHeight() - rightHeight() > 1) {
            // 说明需要右旋
            // 在右旋之前, 需要判断一下. 左子树的右子树是否大于左子树的左子树
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 这里需要先把左子树左旋, 然后在右旋
                left.leftRotate();
                // 随后右旋
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}