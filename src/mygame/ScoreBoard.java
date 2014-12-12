/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author KomalKishor
 */
public class ScoreBoard extends HandlerObserver {

    private HandlerObserver successor = null;

    public ScoreBoard() {
        super();
    }

//    public void update() {
//        
//	}
    public void handleRequest(String request) {
        if (!request.equalsIgnoreCase("TurnCrank")) {
            //if nickle->amount+5,10,25,fake quater
            if(request.equalsIgnoreCase("Nickle"))
            {
            Main.amount=Main.amount+5;    
            }
            else if(request.equalsIgnoreCase("Dime"))
            {
            Main.amount=Main.amount+10;    
            }
            else if(request.equalsIgnoreCase("Quater"))
            {
            Main.amount=Main.amount+25;    
            }
            else if(request.equalsIgnoreCase("FakeQuater"))
            {
                Main.pickerflag=false;
                String txtB2 ="Fake Quarter inserted!!";
                Main.app.getRootNode().attachChild(Main.app.g2);
                Main.app.txt2.setText(txtB2);
                Main.app.getRootNode().attachChild(Main.app.txt2);
                System.out.println(txtB2);   
                Main.audio_fake.playInstance();
                System.out.println("Fake Quarter inserted!!");   
            }
            System.out.println("Amount in Machine = " + Main.amount);
            Main.af=1;
        } else {
            if (successor != null) {
                successor.handleRequest(request);
            }
        }


    }

    @Override
    public void setSuccessor(HandlerObserver next) {
        this.successor = next;
    }
}
