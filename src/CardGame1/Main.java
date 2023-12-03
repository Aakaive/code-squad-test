package CardGame1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer[] deck = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8};
        List<Integer> Card = shuffleCard(deck);
        int turn = 1;
        int remainCard = Card.size();

        while(true){
            showCardTable(Card, Command.BLIND);
            System.out.println("<시도: "+turn+", 남은 카드: "+remainCard+"> 좌표를 두 번 입력하세요.");
            System.out.print("입력 1? ([1~3] [1~6]) : ");
            int selectedRow1 = sc.nextInt();
            int selectedCol1 = sc.nextInt();
            int selectedCardIdx1 = (selectedRow1-1)*6 + (selectedCol1-1);
            System.out.print("입력 2? ([1~3] [1~6]) : ");
            int selectedRow2 = sc.nextInt();
            int selectedCol2 = sc.nextInt();
            int selectedCardIdx2 = (selectedRow2-1)*6 + (selectedCol2-1);

            if(selectedCol1 > 6 || selectedRow1 > 3 || selectedCol2 > 6 || selectedRow2 > 3) {
                System.out.println("\n[잘못된 위치입니다!!]\n");
                continue;
            }
            else if(selectedCol1 == selectedCol2 && selectedRow1 == selectedRow2){
                System.out.println("\n[같은 위치입니다!!]\n");
                continue;
            }
            else if(Card.get(selectedCardIdx1) == 0 || Card.get(selectedCardIdx2) == 0) {
                System.out.println("\n[이미 맞춘 카드입니다!!]\n");
                continue;
            }
            openCard(Card, selectedCardIdx1, selectedCardIdx2);
            turn++;

            if(Card.get(selectedCardIdx1) == Card.get(selectedCardIdx2)) {
                Card.set(selectedCardIdx1, 0);
                Card.set(selectedCardIdx2, 0);
                remainCard -= 2;
            }

            if(Card.size() - Collections.frequency(Card, 0) == Card.stream().distinct().count()-1) {
                showCardTable(Card, Command.CHECK);
                System.out.println("축하합니다!! [게임종료]");
                break;
            }
        }
    }

    private static List<Integer> shuffleCard(Integer[] deck){
        List<Integer> listDeck = Arrays.asList(deck);
        Collections.shuffle(listDeck);
        List<Integer> Card = new ArrayList<>(listDeck.subList(0, 18));
        return Card;
    }

    private static void showCardTable(List<Integer> Card, Command cmd){
        for(int idx = 0; idx < Card.size(); idx++){
            if(cmd == Command.BLIND){
                System.out.print(Card.get(idx) == 0 ? " " : "X");
            }
            else if(cmd == Command.CHECK){
                System.out.print(Card.get(idx) == 0 ? " " : Card.get(idx));
            }

            if((idx+1)%6 == 0) System.out.println();
            else System.out.print(" ");
        }
    }

    private static void openCard(List<Integer> Card, int selectedCard1, int selectedCard2){
        for(int idx = 0; idx < Card.size(); idx++){
            if(selectedCard1 == idx || selectedCard2 == idx) System.out.print(Card.get(idx));
            else System.out.print(Card.get(idx) == 0 ? " " : "X");

            if((idx+1)%6 == 0) System.out.println();
            else System.out.print(" ");
        }
        System.out.println();
    }
}

enum Command {
    BLIND, CHECK
}
