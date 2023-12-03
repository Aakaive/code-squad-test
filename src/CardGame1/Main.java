package CardGame1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer[] deck = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8};

        /*
        List<Integer> card = Arrays.asList(deck);
        Collections.shuffle(card);
        List<Integer> table = new ArrayList<>(card.subList(0, 18));
        int numX1 = 0;
        int numY1 = 0;
        int numX2 = 0;
        int numY2 = 0;
        int numIdx1 = 0;
        int numIdx2 = 0;
        int open = 0;
        int remain = 24;

        while(true) {
            int count = 0;
            for(int i : table) {
                if(i == 0) {
                    System.out.print("  ");
                }
                else {
                    System.out.print("X ");
                }
                if(count == 5 || count == 11 || count == 17) System.out.println("");
                count++;
            }

            System.out.println("<시도 "+ open + ", 남은 카드: "+remain+"> 좌표를 두 번 입력하세요:");
            System.out.println("입력1 ?([숫자1 : 1~3] [숫자2: 1~6])");
            numY1 = sc.nextInt();
            numX1 = sc.nextInt();
            System.out.println("입력2 ?([숫자1 : 1~3] [숫자2: 1~6])");
            numY2 = sc.nextInt();
            numX2 = sc.nextInt();

            if(numX1 > 6 || numY1 > 3 || numX2 > 6 || numY2 > 3) {
                System.out.println("잘못된 위치입니다!!");
                continue;
            }
            else if(numX1 == numX2 && numY1 == numY2){
                System.out.println("같은 위치입니다!!");
                continue;
            }
            else if(table.get(numIdx1) == 0 || table.get(numIdx2) == 0) {
                System.out.println("\n[이미 맞춘 카드입니다!!]\n");
                continue;
            }

            open++;

            numIdx1 = numX1-1 + (numY1-1) * 6;
            numIdx2 = numX2-1 + (numY2-1) * 6;

            count = 0;
            for(int i : table) {
                if(count == numIdx1 || count == numIdx2) {
                    System.out.print(i+" ");
                }
                else {
                    if(i == 0) {
                        System.out.print("  ");
                    }
                    else {
                        System.out.print("X ");
                    }
                }
                if(count == 5 || count == 11 || count == 17) System.out.println("");
                count++;
            }

            if(table.get(numIdx1) == table.get(numIdx2)) {
                table.set(numIdx1, 0);
                table.set(numIdx2, 0);
                remain -= 2;
            }

            System.out.println();

            if(Collections.frequency(table, 0) >= 2) {
                if(table.size() - Collections.frequency(table, 0) == table.stream().distinct().count()-1) {
                    count = 0;
                    for(int i : table) {
                        if(i == 0){
                            System.out.print(" ");
                        }
                        else {
                            System.out.print(i+" ");
                        }
                        if(count == 5 || count == 11 || count == 17) System.out.println("");
                        count++;
                    }
                    System.out.println("축하합니다! [게임 종료]");
                    break;
                }
            }
        }*/

    }
}
