package com.ty.tree.threadedbinarytree;


/**
 * program : OneCode
 * description : 线索二叉树
 * author : jyf
 * date : 2020-08-13 11:44
 **/
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        int no = 6;
        System.out.println(no+"节点的上一个节点是"+node3.getLeft());
        System.out.println(no+"节点的下一个节点是"+node3.getRight());

        // 中序遍历输出我们的线索数
        threadedBinaryTree.preThreaded();
    }
}

// 表示树
class ThreadedBinaryTree{

    private HeroNode root; // 表示跟节点

    // 还需要一个额外的节点记录上一个节点
    private HeroNode pre = null; // 默认为null

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder(){
        if (root == null){
            System.out.println("当前树为空");
        }else{
            root.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder(){
        if (root == null){
            System.out.println("当前树为空");
        }else{
            root.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder(){
        if (root == null){
            System.out.println("当前树为空");
        }else{
            root.postOrder();
        }
    }

    // 前序查找
    public HeroNode preSearch(int no){
        if (this.root == null){
            System.out.println("当前节点为空");
            return null;
        }else{
            return root.preSearch(no);
        }
    }

    // 中序查找
    public HeroNode infixSearch(int no){
        if (this.root == null){
            System.out.println("当前节点为空");
            return null;
        }else{
            return root.infixSearch(no);
        }
    }
    // 后序查找
    public HeroNode postSearch(int no){
        if (this.root == null){
            System.out.println("当前节点为空");
            return null;
        }else{
            return root.postSearch(no);
        }
    }

    // 删除节点
    public void deleteNode(int no){
        // 首先我们需要对root进行非空校验
        if (root != null){
            if (root.getNo() == no){
                root = null;
                return;
            }
            root.deleteNode(no);
        }else{
            System.out.println("当前树为空");
        }
    }

    // 中序遍历线索化节点
    public void preThreaded(){
        // 首先中序遍历我们还是需要一直找到最左边的节点
        HeroNode node = root;

        while (node != null){
            // 因为现在是线索了, 所以我们第一个找到的肯定是leftType为1的数
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 上面遍历完了, 就已经到了第一个最左边的叶子节点
            System.out.println(node);
            // 因为线索化了, 所以我们直接可以输出他的right的节点
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            // 没有线索的话,那说明有叶子结点
            node= node.getRight();
        }
    }

    // 线索化节点. 中序线索 我们需要在树这个类中完成
    public void threadedPreNodes(HeroNode node) {
        // 首先我们需要确定, 我们是中序线索化,
        // 线索化其实就是把每一次的跳转,进行连起来
        // 首先需要判断root节点是否为空
        if (node == null ){
            return;
        }

        // 我们中序需要先判断左边是否有节点
         threadedPreNodes(node.getLeft());
        // 到这里表示已经没有右子节点了
        if (node.getLeft() == null){
            // 说明这是一个叶子结点
            node.setLeft(pre); // 指向上一个节点
            node.setLeftType(1); // 设置类型
        }

        if (pre!=null && pre.getRight() == null){
            // 说明上一个节点的right还没有指向是一个叶子节点, 我们需要把它指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 最后把pre 变成当前节点去下一个操作
        pre = node;


        // 右子节点的递归
        threadedPreNodes(node.getRight());
    }

    public void threadedNodes() {
        this.threadedPreNodes(root);
    }
}


// 表示节点
class HeroNode{
    private int no;
    private String name;  // 名字
    private HeroNode left; // 左节点
    private HeroNode right;// 右节点

    // 需要两个额外的属性, 来表示当前节点的属性是叶子节点还是非叶子节点
    private int leftType = 0; // 0表示指向左子树  1表示指向前去节点
    private int rightType = 0; // 表示右节点的属性



    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历
    public void preOrder(){
        // 前序是先输出当前节点在像左遍历, 随后向后
        System.out.println(this);
        // 向左遍历
        if (this.left != null) this.left.preOrder();
        // 向右遍历
        if (this.right != null) this.right.preOrder();
    }

    // 中序遍历
    public void infixOrder(){
        // 中序是先判断左节点, 如果左节点为空在 输出当前, 随后遍历右节点
        // 先问左边节点有没有
        if (this.left != null) this.left.infixOrder();
        // 在输出当前
        System.out.println(this);
        // 在问右边节点
        if (this.right != null) this.right.infixOrder();
    }

    // 后续遍历
    public void postOrder(){
        // 后续遍历是先判断左边有没有, 在看右边有没有, 都没有在回来输出当前节点
        // 问左边
        if (this.left != null) this.left.postOrder();
        // 问右边
        if (this.right != null) this.right.postOrder();
        // 输出当前
        System.out.println(this);
    }
    // 删除某个节点
    public void deleteNode(int no){
        // 删除节点我们使用前序查找的方式来删除, 删除我们需要判断下一个节点是否等于no
        //  因为我们无法删除我们本身这个节点,只能通过上一个节点来删除我们这个节点
        // 1. 我们首先判断当前节点的下两个节点是否 等于no 如果等于,那我们直接删除即可,
        // 2. 如果不等于,那我们对下一个节点进行递归删除
        if (this.left != null && this.left.no == no ){
            // 表示找到了改节点, 那我们直接删除即可
            this.left = null;
            return;
        }

        // 判断右节点是否等于
        if (this.right != null && this.right.no == no ){
            // 表示找到了改节点, 那我们直接删除即可
            this.right = null;
            return;
        }

        // 都不是进行递归调用
        if (this.left != null){
            // 不为空才进行调用
            this.left.deleteNode(no);
        }

        if (this.right!= null){
            this.right.deleteNode(no);
        }
    }

    // 前序查找
    public HeroNode preSearch(int no){
        // 前序查找就是先比较当前节点, 在比较左节点, 随后比较右节点
        System.out.println("前序查找");
        if (this.no == no){
            return this;
        }
        // 如果当前不是向左边找
        HeroNode heroNode = null;
        if (this.left != null){
            heroNode = this.left.preSearch(no);
        }
        // 如果下一个还是null 说明没找到
        if (heroNode != null){
            return heroNode;
        }
        // 到这里说明没找到  向右节点找
        if (this.right != null){
            heroNode = this.right.preSearch(no);
        }
        // 如果找完了那直接返回即可
        return heroNode;
    }

    // 中序查找
    public HeroNode infixSearch(int no){
        // 中序查找首先判断左边有没有节点, 如果有就去左边, 判断当前, 随后判断右边
        HeroNode heroNode = null;
        // 先判断左边
        if (this.left != null){
            heroNode = this.left.infixSearch(no);
        }
        // 判断是否为空
        if (heroNode != null){
            return heroNode;
        }
        System.out.println(" 中序查找");
        // 经过上面说明左边没找到, 随后比较当前节点
        if (this.no == no){
            return this;
        }

        // 还是没找到说明要向右边找
        if (this.right != null){
            heroNode = this.right.infixSearch(no);
        }
        // 直接返回即可
        return heroNode;
    }

    // 后续查找
    public HeroNode postSearch(int no){
        // 后续查找是先判断左边, 在判断右边, 随后比较当前
        HeroNode heroNode = null;
        if (this.left != null){
            heroNode =  this.left.postSearch(no);
        }
        // 判断是否为空
        if (heroNode != null){
            return heroNode;
        }

        // 没找到判断右边
        if (this.right != null){
            heroNode = this.right.postSearch(no);
        }
        // 判断是否为空
        if (heroNode != null){
            return heroNode;
        }

        System.out.println(" 后续查找");
        // 都没找到判断当前
        if (this.no == no){
            return this;
        }
        return null;
    }

}
