public class ExceptionLesson {

            public static void main(String[] args)
            {
                System.out.println("Program starts");

                try
                {
                    System.out.println("Before method1 calling");
                    System.out.println(3/0);
                    System.out.println("After method1 calling. Never will be shown");
                }
                catch (Exception e)
                {
                    System.out.println("Before method1 calling");
                }

                System.out.println("Program is still running");
            }

            public static void method1()
            {
                int a = 100;
                int b = 0;
                System.out.println(a / b);
            }
        }