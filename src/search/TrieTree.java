package search;

public class TrieTree {

    public char data;

    public TrieTree[] children;

    public boolean isEnd;

    public TrieTree(char data) {
        this.data = data;
        children = new TrieTree[26];
    }

    /**
     * 只能存储[a-z]
     * @param array
     * @param root
     */
    public void insert(char[] array, TrieTree root) {
        for (int i = 0; i < array.length; i++) {
            char data = array[i];
            int index = data - 'a';
            if (root.children[index] == null) {
                TrieTree child = new TrieTree(data);
                root.children[index] = child;
            }
            root = root.children[index];
        }
        root.isEnd = true;
    }

    public boolean search(char[] array, TrieTree root) {
        for (int i = 0; i < array.length; i++) {
            char data = array[i];
            int index = data - 'a';
            if (root.children[index] == null) {
                return false;
            }
            root = root.children[index];
        }
        return root.isEnd;
    }

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree('/');
        char[] how = new char[]{'h', 'o', 'w'};
        char[] hi = new char[]{'h', 'i'};
        char[] her = new char[]{'h', 'e', 'r'};
        char[] hello = new char[]{'h', 'e', 'l', 'l', 'o'};
        char[] so = new char[]{'s', 'o'};
        char[] see = new char[]{'s', 'e', 'e'};

        trieTree.insert(how, trieTree);
        trieTree.insert(hi, trieTree);
        trieTree.insert(her, trieTree);
        trieTree.insert(hello, trieTree);
        trieTree.insert(so, trieTree);
        trieTree.insert(see, trieTree);

        System.out.println(trieTree);

        char[] hoo = new char[]{'h', 'o', 'o'};
        System.out.println(trieTree.search(hoo, trieTree));
    }

}
