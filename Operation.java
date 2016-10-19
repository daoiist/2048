import java.awt.event.*;
import javax.swing.*;
 
public class Operation implements KeyListener{
    Block[] block;
    JPanel panel;
    boolean numFlag;
    boolean overFlag = true;
    int sum;
    public Operation(JFrame frame) {
        this.panel = (JPanel)frame.getContentPane();
        block = new Block[16];
        numFlag = true;
        addBlock();
        for (int i = 0; i < 2; i++)
            appearBlock();
        frame.addKeyListener(this);
 
    }
 
    private void addBlock() {
        for (int i = 0; i < 16; i++) {
            block[i] = new Block();
            block[i].setHorizontalAlignment(JLabel.CENTER);
            // ²»Í¸Ã÷
            block[i].setOpaque(true);
            panel.add(block[i]);
             
        }
    }
 
    public void appearBlock() {
        while (overFlag&&sum<16) {
            int index = (int) (Math.random() * 16);
            if (block[index].getValue() == 0) {
                if (Math.random() < 0.8)
                    block[index].setValue(2);
                else
                    block[index].setValue(4);
                break;
            }
        }
    }
 
    public void judgeAppear() {
        sum = 0;
        boolean flag =false;
        for (int i = 0; i < 16; i++) {
            if (block[i].getValue() != 0)
                sum++;
        }
        if(sum==16){
    	for(int i=0;i<=12;i+=4)
    		for(int j =i;j<=i+2;j++){
    			if(block[j].getValue()==block[j+1].getValue()){
    				flag=true;
    			}
    		}
    	for(int i=0;i<=3;i++)
    		for(int j =i;j<=i+8;j+=4){
    			if(block[j].getValue()==block[j+4].getValue()){
    				flag=true;
    			}
    		}
        }

        if (sum == 16&&!flag){
        	overFlag = false;
        }
 
    }
 
    public void upBlock() {
 
        for (int i = 0; i < 4; i++) {
        	for(int j = i+4; j<=i+12; j+=4){
        		if(block[j-4].getValue()==block[j].getValue()){
        			block[j-4].setValue(block[j-4].getValue()+block[j].getValue());
        			if(block[j-4].getValue()==2048)
        				win();
        			block[j].setValue(0);
        			upRemoveBlank();
        		}
        	}
        }
 
    }
 
    public void upRemoveBlank(){
    	for(int i = 0; i<=3 ; i++){
    		for(int j = i+4; j<=i+12; j+=4){
    			int k = j;
    			while(block[j-4].getValue()==0&&k-4>=i){
    				block[j-4].setValue(block[j].getValue());
    				block[j].setValue(0);
    				k-=4;
    			}
    		}
    	}
    }
    public void downBlock() {
 
        for (int i = 0; i < 4; i++) {
        	for(int j = i+12; j>=i+4;j-=4){
        		if(block[j].getValue()==block[j-4].getValue()){
        			block[j].setValue(block[j].getValue()+block[j-4].getValue());
        			if(block[j].getValue()==2048)
        				win();
        			block[j-4].setValue(0);
        			downRemoveBlank();
        		}
        	}
        }
 
    }
    
    public void downRemoveBlank(){
    	for(int i = 0; i<=3 ; i++){
    		for(int j = i+12; j>=i+4; j-=4){
    			int k = j;
    			while(block[j].getValue()==0&&k<=i+12){
    				block[j].setValue(block[j-4].getValue());
    				block[j-4].setValue(0);
    				k+=4;
    			}
    		}
    	}
    }
    
    public void rightBlock() {
 
        for (int i = 3; i <= 15; i += 4) {
        	for(int j = i;j>=i-2;j--){
        		if(block[j].getValue()==block[j-1].getValue()){
        			block[j].setValue(block[j-1].getValue()+block[j].getValue());
        			if(block[j].getValue()==2048)
        				win();
        			block[j-1].setValue(0);
        			rightRemoveBlank();
        		}
        	}
        }
 
    }
    public void rightRemoveBlank(){
    	for(int i = 3; i<=15 ; i+=4){
    		for(int j = i; j>=i-2; j--){
    			int k = j;
    			while(block[j].getValue()==0&&k<=i){
    				block[j].setValue(block[j-1].getValue());
    				block[j-1].setValue(0);
    				k++;
    			}
    		}
    	}
    }
    public void leftBlock() {
 
        for (int i = 0; i <= 12; i += 4) {
        	for(int j = i; j<=i+2; j++){
        		if(block[j].getValue()==block[j+1].getValue()){
        			block[j].setValue(block[j].getValue()+block[j+1].getValue());
        			if(block[j].getValue()==2048)
        				win();
        			block[j+1].setValue(0);
        			leftRemoveBlank();
        		}
        	}
        }
 
    }
    public void leftRemoveBlank(){
    	for(int i = 0; i<=12 ; i+=4){
    		for(int j = i; j<=i+2; j++){
    			int k = j;
    			while(block[j].getValue()==0&&k>=i){
    				block[j].setValue(block[j+1].getValue());
    				block[j+1].setValue(0);
    				k--;
    			}
    		}
    	}
    }
    public void over() {
        if (!overFlag) {
            block[4].setText("G");
            block[5].setText("A");
            block[6].setText("M");
            block[7].setText("E");
            block[8].setText("O");
            block[9].setText("V");
            block[10].setText("E");
            block[11].setText("R");
             
            block[11].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    reStart();
                }
            });
        }
    }
     
    public void win() {
         
        block[0].setText("Y");
        block[1].setText("O");
        block[2].setText("U");
        block[13].setText("W");
        block[14].setText("I");
        block[15].setText("N");
         
        block[15].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reStart();
            }
        });
 
    }
    public void reStart(){
        overFlag=true;
        for(int i=0;i<16;i++)
            block[i].setValue(0);
        for (int i = 0; i < 2; i++)
            appearBlock();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
        	upRemoveBlank();
            upBlock();
            judgeAppear();
            appearBlock();
            over();
            break;
        case KeyEvent.VK_DOWN:
        	downRemoveBlank();
        	downBlock();
            judgeAppear();
            appearBlock();
            over();
            break;
        case KeyEvent.VK_LEFT:
        	leftRemoveBlank();
        	leftBlock();
            judgeAppear();
            appearBlock();
            over();
            break;
        case KeyEvent.VK_RIGHT:
        	rightRemoveBlank();
        	rightBlock();
            judgeAppear();
            appearBlock();
            over();
            break;
        }
 
    }
 
     
    @Override
    public void keyTyped(KeyEvent e) {
 
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
 
    }
 
}