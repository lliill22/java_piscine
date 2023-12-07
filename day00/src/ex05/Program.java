package ex05;

import java.util.Scanner;

public class Program {

    private final static int MAX_LENGTH_NAME = 10;
    private final static int MAX_STUDENTS = 10;
    private final static int START_TIME_LESSONS = 1;
    private final static int FINISH_TIME_LESSONS = 6;
    private static Scanner scanner = new Scanner(System.in);
    private static String[] nameOfStudents = new String[MAX_STUDENTS + 1];
    private static String[][] classes = new String[8][10];
    private static String[][][][] attendance = new String[10][31][10][1];


    public static void main(String[] args) {

        getNamesOfStudents(nameOfStudents, scanner);
        getTimeClasses(classes, scanner);
        getAttendanceRecording(attendance, classes, nameOfStudents, scanner);
        printResult(classes, nameOfStudents, attendance);
        scanner.close();
    }

    private static void printErrorAndExit(String msg, Scanner scanner) {
        System.err.println(msg);
        scanner.close();
        System.exit(-1);
    }

    private static void getNamesOfStudents(String[] nameOfStudents, Scanner scanner) {
        for (int i = 0; i <= MAX_STUDENTS; i++) {
            nameOfStudents[i] = scanner.nextLine();
            if (nameOfStudents[i].length() > MAX_LENGTH_NAME) {
                printErrorAndExit("Maximum length of a student’s name is 10 (no spaces).", scanner);
            }
            if (nameOfStudents[i].equals(".")) {
                nameOfStudents[i] = null;
                return;
            }
            if (i == 10) {
                printErrorAndExit("Maximum number of students in the timetable is 10.", scanner);
            }
        }
    }

    private static void fillClasses(String[] classes, String time, Scanner scanner) {
        int i = 0;

        for (; i < classes.length && classes[i] != null; i++) ;

        if (i < 10) {
            int tmpTime = Integer.parseInt(time);
            if (tmpTime < START_TIME_LESSONS || tmpTime > FINISH_TIME_LESSONS) {
                printErrorAndExit("Maximum number of students in the timetable is 10.", scanner);
            }
            classes[i] = time;
        }
    }

    private static void getTimeClasses(String[][] classes, Scanner scanner) {
        String time = scanner.next();
        String day;

        while (!time.equals(".")) {
            day = scanner.next();
            if (day.equals("MO")) {
                fillClasses(classes[1], time, scanner);
            } else if (day.equals("TU")) {
                fillClasses(classes[2], time, scanner);
            } else if (day.equals("WE")) {
                fillClasses(classes[3], time, scanner);
            } else if (day.equals("TH")) {
                fillClasses(classes[4], time, scanner);
            } else if (day.equals("FR")) {
                fillClasses(classes[5], time, scanner);
            } else if (day.equals("SA")) {
                fillClasses(classes[6], time, scanner);
            } else if (day.equals("SU")) {
                fillClasses(classes[7], time, scanner);
            }
            time = scanner.next();
        }
    }

    private static int findDayAndClassOfWeek(int d, String[][] classes, String time) {
        int week = d % 7 + 1;
        int i = 0;

        for (; i < 10 && !classes[week][i].equals(time); i++) ;

        if (i < 10) {
            return i;
        }
        return 0;
    }

    private static void getAttendanceRecording(String[][][][] attendance, String[][] classes, String[] nameOfStudents, Scanner scanner) {
        String name = scanner.next();
        String time;

        while (!name.equals(".")) {
            int n, d, t;
            time = scanner.next();
            d = scanner.nextInt();
            String here = scanner.next();

            for (n = 0; n < nameOfStudents.length && !nameOfStudents[n].equals(name); n++) ;
            t = findDayAndClassOfWeek(d, classes, time);
            attendance[n][d][t][0] = here;
            name = scanner.next();
        }
    }

    private static void findTimeOfClassAndPrint(int i, String[][] classes) {
        if (i == 1) {
            System.out.print("     ");
        }
        int j = i % 7 + 1;

        for (int l = 0; l < 10 && classes[j][l] != null; l++) {
            System.out.print(classes[j][l] + ":00 ");

            if (j == 1) {
                System.out.print("MO  " + i + "|");
            } else if (j == 2) {
                System.out.print("TU  " + i + "|");
            } else if (j == 3) {
                System.out.print("WE  " + i + "|");
            } else if (j == 4) {
                System.out.print("TH  " + i + "|");
            } else if (j == 5) {
                System.out.print("FR  " + i + "|");
            } else if (j == 6) {
                System.out.print("SA  " + i + "|");
            } else if (j == 7) {
                System.out.print("SU  " + i + "|");
            }
        }

        if (i == 30) {
            System.out.println();
        }
    }

    private static void findAttendanceAndPrint(String[][][] strings, String[][] classes, int len) {
        boolean isFirst = true;

        for (int i = 1; i < 31; i++) {
            for (int j = 0; j < 10 && classes[i % 7 + 1][j] != null; j++) {
                if (isFirst) {
                    isFirst = false;

                    for (int z = 0; z < 13 - len; z++) {
                        System.out.print(" ");
                    }
                }

                if (i > 9) {
                    System.out.print(" ");
                }

                if (strings[i][j][0] != null && strings[i][j][0].equals("HERE")) {
                    System.out.print(" 1|        ");
                } else if (strings[i][j][0] != null && strings[i][j][0].equals("NOT_HERE")) {
                    System.out.print("-1|        ");
                } else {
                    System.out.print("  |        ");
                }
            }
        }
        System.out.println();
    }

    private static void printResult(String[][] classes, String[] nameOfStudents, String[][][][] attendance) {
        for (int i = 1; i < 31; i++) {
            findTimeOfClassAndPrint(i, classes);
        }

        for (int i = 0; i < nameOfStudents.length && nameOfStudents[i] != null; i++) {
            System.out.print(nameOfStudents[i]);
            findAttendanceAndPrint(attendance[i], classes, nameOfStudents[i].length());
        }
    }
}