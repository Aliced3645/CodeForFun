class BiNode{
    public BiNode node1, node2;
    public int data;

    public BiNode(int _data){
        data = _data;
    }
}



public static BiNode constructTree(int[] array, int low, int high){
        if(low > high)
            return null;
        int mid = (low + high) / 2;
        BiNode node = new BiNode(array[mid]);
        node.node1 = constructTree(array, low, mid - 1);
        node.node2 = constructTree(array, mid + 1, high);
        return node;
}

public static void inOrderTraverse(BiNode root){
        if(root == null)
            return;
        inOrderTraverse(root.node1);
        System.out.print(root.data + " ");
        inOrderTraverse(root.node2);
    }



