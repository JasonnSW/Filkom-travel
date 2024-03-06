import java.util.Scanner;

public class nathan_051_2_1 {
    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        // int loop = than.nextInt();
        // than.nextLine();
        int numCommands = than.nextInt();
        // LinkedList list = new LinkedList();
        than.nextLine();
        // list.makeEmpty();
        SinglyLinkedList sll = new SinglyLinkedList();
        // for (int i = 0; i < loop; i++) {
            for (int i = 0; i < numCommands; i++) {
                String command = than.nextLine();
                String[] parts = command.split(" ");
            //     String perintah = than.nextLine();
        //     String perintah1 = perintah.substring(0, perintah.indexOf(" "));
        //     int simpan = perintah.indexOf(" ");
        //     perintah = perintah.substring(simpan + 1, perintah.length());
        //     String perintah2 = "";
        //     int input = 0;
        //     if (perintah1.equals("TAMBAH") || perintah1.equals("TAMBAH ")) {
        //         // pakek!!!!!
        //         perintah2 = perintah.substring(0, perintah.indexOf(" "));
        //         input = Integer.parseInt(perintah.substring(perintah.indexOf(" ") + 1, perintah.length()));
        //         if (perintah2.equals("AWAL") || perintah2.equals("AWAL ") ) {
        //             list.prepend(input);
        //         } else if (perintah2.equals("AKHIR" ) || perintah2.equals("AKHIR ")) {
        //             list.append(input);
        //         }

        //     } else if (perintah1.equals("HAPUS")  || perintah1.equals("HAPUS ")) {
        //         perintah2 = perintah.substring(0, perintah.length());
        //             if (perintah2.equals("AWAL") || perintah2.equals("AWAL ")) {
        //                 list.removeFirst();
        //             } else if (perintah2.equals("AKHIR") || perintah2.equals("AKHIR ")) {
        //                 list.removeLast();
                    
        //         }
        //     } else if (perintah1.equals("LIHAT") || perintah1.equals("LIHAT ")) {
        //             list.printList();
        //     } else if (perintah1.equals("ADA") || perintah1.equals("ADA ")) {
        //         // pakek!!!!!
        //         perintah2 = perintah.substring(0, perintah.indexOf(" "));
        //         input = Integer.parseInt(perintah.substring(perintah.indexOf(" ") + 1, perintah.length()));
        //         list.search(input);
        //     } else if (perintah1.equals("BERAPA") || perintah1.equals("BERAPA ")) {
        //             list.getLength();
                
        //     }
        //     // System.out.println(perintah1);

        // }

            switch (parts[0]) {

                case "TAMBAH":
                    if (parts[1].equals("AWAL")) {
                        int data = Integer.parseInt(parts[2]);
                        sll.insertAtBeginning(data);
                    } else if (parts[1].equals("AKHIR")) {
                        int data = Integer.parseInt(parts[2]);
                        sll.insertAtEnd(data);
                    }
                    break;
                case "HAPUS":
                    if (parts[1].equals("AWAL")) {
                        sll.deleteAtBeginning();
                    } else if (parts[1].equals("AKHIR")) {
                        sll.deleteAtEnd();
                    }
                    break;
                case "ADA":
                    int data = Integer.parseInt(parts[2]);
                    if (sll.searchData(data)) {
                        System.out.println("ADA");
                    } else {
                        System.out.println("MAAF YAH KAMU SEDANG TIDAK BERUNTUNG, TERNYATA DATANYA GAK ADA");
                    }
                    break;
                case "LIHAT":
                    sll.printLinkedList();
                    break;
                case "BERAPA":
                    System.out.println(sll.countData());
                    break;
                default:
                    break;
            }
        }

        than.close();
    }
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void deleteAtBeginning() {
        if (head == null) {
            System.out.println("Data Linked List kosong");
        } else {
            head = head.next;
        }
    }

    public void deleteAtEnd() {
        if (head == null) {
            System.out.println("Data Linked List kosong");
        } else if (head.next == null) {
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
    }

    public boolean searchData(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void printLinkedList() {
        if (head == null) {
            System.out.println("Data Linked List kosong");
        } else {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    public int countData() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
