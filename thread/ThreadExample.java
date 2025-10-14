public class ThreadExample {

    public static void main(String[] args) {
        PrintNumbersSystemOut threadOut = new PrintNumbersSystemOut();
        PrintNumbersSystemErr threadErr = new PrintNumbersSystemErr();

        threadOut.start();
        threadErr.start();
    }

    static class PrintNumbersSystemOut extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(j);
                }
                System.out.print(" ");
            }
        }
    }

    static class PrintNumbersSystemErr extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    System.err.print(j);
                }
                System.err.print(" ");
            }
        }
    }
}
