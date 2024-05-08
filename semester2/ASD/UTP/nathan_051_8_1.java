// package ASD.UTP;
// package ASD.Praktikum;

import java.util.Scanner;

public class nathan_051_8_1 {
    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        AVL avl = new AVL();
        int n = than.nextInt();
        than.nextLine();
        for (int i = 0; i < n; i++) {
            String perintah = than.nextLine();
            switch (perintah) {
                case "TAMBAH BARANG":
                    String[] items = than.nextLine().split(";");
                    for (String item : items) {
                        String[] details = item.split(",");
                        String nama = details[0];
                        int harga = Integer.parseInt(details[1]);
                        avl.root = avl.insert(avl.root, nama, harga);
                        // avl.preOrder(avl.root);
                    }
                    System.out.println("Data " + items.length + " barang berhasil ditambah");
                    break;
                case "CARI BARANG":
                    String[] cari = than.nextLine().split(" ");
                    int hargaCari = Integer.parseInt(cari[2]);
                    if (!avl.search(avl.root, cari[0], hargaCari)) {
                        System.out.println("Data barang tidak ditemukan");
                    }
                    break;
            }
            System.out.println();
        }
    }
}

class NodeAVL {
    String name;
    int price;
    int height;
    NodeAVL left, right;

    NodeAVL(String name, int price) {
        this.name = name;
        this.price = price;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

class AVL {
    NodeAVL root;

    int height(NodeAVL node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    int getBalance(NodeAVL node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    NodeAVL rightRotate(NodeAVL y) {
        NodeAVL x = y.left;
        NodeAVL T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    NodeAVL leftRotate(NodeAVL x) {
        NodeAVL y = x.right;
        NodeAVL T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    NodeAVL insert(NodeAVL node, String name, int price) {
        if (node == null)
            return (new NodeAVL(name, price));

        if (price < node.price)
            node.left = insert(node.left, name, price);
        else if (price > node.price)
            node.right = insert(node.right, name, price);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && price < node.left.price)
            return rightRotate(node);

        if (balance < -1 && price > node.right.price)
            return leftRotate(node);

        if (balance > 1 && price > node.left.price) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && price < node.right.price) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void preOrder(NodeAVL node) {
        if (node != null) {
            System.out.println(node.name + " = Rp" + node.price);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    int count = 0;
    boolean found = false;

    NodeAVL kurangDari(NodeAVL root, int price) {
        if (root == null) {
            return null;
        }
        if (root.price < price) {
            found = true;
            System.out.println(++count + ". " + root.name + " = Rp" + root.price);
            kurangDari(root.left, price);
            kurangDari(root.right, price);
        } else if (root.price == price) {
            kurangDari(root.left, price);
        } else {
            kurangDari(root.left, price);
        }
        return root;
    }

    NodeAVL lebihDari(NodeAVL root, int price) {
        if (root == null) {
            return null;
        }
        if (root.price > price) {
            found = true;
            lebihDari(root.left, price);
            lebihDari(root.right, price);
            System.out.println(++count + ". " + root.name + " = Rp" + root.price);
        } else if (root.price == price) {
            lebihDari(root.right, price);
        } else {
            lebihDari(root.right, price);
        }
        return root;
    }

    NodeAVL samaDengan(NodeAVL root, int price) {
        if (root == null) {
            return null;
        }
        if (root.price == price) {
            found = true;
            System.out.println(root.name + " = Rp" + root.price);
            samaDengan(root.left, price);
            samaDengan(root.right, price);
        } else if (root.price < price) {
            samaDengan(root.right, price);
        } else {
            samaDengan(root.left, price);
        }
        return root;
    }

    int nomorLebih = 1;
    int nomorKurang = 1;

    boolean search(NodeAVL root, String condition, int harga) {
        if (root == null) {
            return false;
        }
        if (condition.equals("LEBIH")) {
            boolean found = false;
            boolean leftFound = search(root.right, condition, harga);
            if (root.price > harga) {
                System.out.println(nomorLebih++ + ". " + root.name + " = Rp" + root.price);
                found = true;
            }
            boolean rightFound = search(root.left, condition, harga);
            return found || leftFound || rightFound;
        } else if (condition.equals("KURANG")) {
            boolean rightFound = search(root.right, condition, harga);
            boolean found = false;
            if (root.price < harga) {
                System.out.println(nomorKurang++ + ". " + root.name + " = Rp" + root.price);
                found = true;
            }
            boolean leftFound = search(root.left, condition, harga);

            return leftFound || found || rightFound;
        } else if (condition.equals("SAMA")) {
            boolean leftFound = search(root.left, condition, harga);
            boolean found = false;
            if (root.price == harga) {
                System.out.println(root.name + " = Rp" + root.price);
                found = true;
            }
            boolean rightFound = search(root.right, condition, harga);
            return leftFound || found || rightFound;
        }
        return false;
    }

}