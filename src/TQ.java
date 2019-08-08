public class TQ {
    public static void main(String[] args) throws Exception {
//        CircularQueue<Integer> circularQueue = new CircularQueue(5);
//
//        circularQueue.setElement(1);
//        circularQueue.setElement(2);
//        circularQueue.setElement(3);
//        circularQueue.setElement(4);
//        circularQueue.setElement(5);
//        System.out.println("CircularQueue is full " + circularQueue);
//
//        System.out.print("Getting following element from circular Queue ");
//        System.out.println(circularQueue.getElement());
//        System.out.println("--------------------------------");
//        System.out.print("Getting following element from circular Queue ");
//        System.out.println(circularQueue.getElement());
//        circularQueue.setElement(6);
//        System.out.println("After setting element [6]  into  circular Queue ");
//        System.out.println(circularQueue);
//    }
        CircularQueue<Integer> buffer = new CircularQueue<>(10);

        for (int i = 0; i <5 ; i++) {
            new Thread(new Producer(i+1,0 ,buffer)).start();
        }

        Thread.sleep(100);

        for (int i = 0; i < 4; i++) {
            new Thread(new Consumer<Integer>(buffer, i + 1)).start();
        }
    }
}

