package CardGame2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer[] deck = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8};
        List<Integer> Card = shuffleCard(deck);
        int turnCnt = 1;
        int remainCard = Card.size();
        PlayerTurn turn = PlayerTurn.P1;

        System.out.print("P1의 이름을 입력하세요. : ");
        Player p1 = new Player(sc.nextLine());
        System.out.print("P2의 이름을 입력하세요. : ");
        Player p2 = new Player(sc.nextLine());

        while(true){
            System.out.println("<턴: "+turnCnt+", 남은 카드: "+remainCard+">");
            showCardTable(Card, CardGame2.Command.BLIND);
            System.out.println("<"+p1.name+"의 점수: "+p1.score+" / "+p2.name+"의 점수: "+p2.score+">\n");
            System.out.println("["+(turn == PlayerTurn.P1 ? p1.name : p2.name)+"의 턴!!]");
            System.out.println("[카드 두 장의 위치를 입력하세요!]");
            System.out.print("첫번째 카드의 위치는? ([1~3] [1~6]) : ");
            int selectedRow1 = sc.nextInt();
            int selectedCol1 = sc.nextInt();
            int selectedCardIdx1 = (selectedRow1-1)*6 + (selectedCol1-1);
            System.out.print("두번째 카드의 위치는? ([1~3] [1~6]) : ");
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
            System.out.println("\n[카드 오픈!!]\n");
            openCard(Card, selectedCardIdx1, selectedCardIdx2);

            if(Card.get(selectedCardIdx1).equals(Card.get(selectedCardIdx2))) {
                System.out.println("[정답!! " + (turn == PlayerTurn.P1 ? p1.name : p2.name) + "가 " + (turn == PlayerTurn.P1 ? p1.BonusScore : p2.BonusScore) + "점 획득!!" + "]\n");
                if(turn == PlayerTurn.P1) p1.getScore();
                else p2.getScore();
                Card.set(selectedCardIdx1, 0);
                Card.set(selectedCardIdx2, 0);
                remainCard -= 2;
            }
            else {
                System.out.println("[오답!!]\n");
                if(turn == PlayerTurn.P1) p1.BonusScore = 10;
                else p2.BonusScore = 10;
                turn = (turn == PlayerTurn.P1 ? PlayerTurn.P2 : PlayerTurn.P1);
            }

            if(Card.size() - Collections.frequency(Card, 0) == Card.stream().distinct().count()-1) {
                System.out.println("<턴: "+turnCnt+", 남은 카드: "+remainCard+">");
                System.out.println(p1.name+"의 점수 : "+p1.score);
                System.out.println(p2.name+"의 점수 : "+p2.score);
                showCardTable(Card, CardGame2.Command.CHECK);
                if(p1.score == p2.score) {
                    System.out.println("무승부!!!");
                }
                else {
                    System.out.println("[축하합니다!!! " + (p1.score > p2.score ? p1.name : p2.name) + "의 승리!!!]\n");
                }
                break;
            }
            turnCnt++;
        }
    }

    private static List<Integer> shuffleCard(Integer[] deck){
        List<Integer> listDeck = Arrays.asList(deck);
        Collections.shuffle(listDeck);
        return listDeck.subList(0, 18);
    }

    private static void showCardTable(List<Integer> Card, CardGame2.Command cmd){
        for(int idx = 0; idx < Card.size(); idx++){
            if(cmd == CardGame2.Command.BLIND){
                System.out.print(Card.get(idx) == 0 ? " " : "X");
            }
            else if(cmd == CardGame2.Command.CHECK){
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

enum PlayerTurn {
    P1, P2
}

class Player { // 생성자 만들기. generate - constructor
    String name;
    int score;
    int BonusScore;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.BonusScore = 10;
    }

    public void getScore() {
        score += BonusScore;
        BonusScore *= 2;
    }
    // 스코어 획득도 클래스 내부에 새로운 메소드 선언하여 캡슐화 해주기.
}