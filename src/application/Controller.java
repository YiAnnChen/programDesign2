package application;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controller {
	
	@FXML
	private Canvas mainCanvas;
	
    private Random rand = new Random();
    private Square[][] square;
    private static final int goal = 2048;
    private static int highest = 0;
    private boolean checkMove;
    
    enum State{
    	start, running,gameOver,win
    }
    
	private static State gameState = State.start;
	
	public void initFace() {
    	grid(mainCanvas.getGraphicsContext2D());
    }
	
	@FXML
	private void clickToStart() {
		if(gameState != State.running) {
			gameState = State.running;
			square = new Square[4][4];
			randomSquare();
			randomSquare();
			grid(mainCanvas.getGraphicsContext2D());
		}
	}
	
	@FXML
    private void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                left();
                break;
            case RIGHT:
                right();
                break;
            case UP:
                up();
                break;
            case DOWN:
                down();
                break;
            default:
                break;
        }
        grid(mainCanvas.getGraphicsContext2D());
    }
    
    private void grid(GraphicsContext g) {
    	
    	if(gameState == State.start) {
    		g.setFill(Color.rgb(237,226,219));
        	g.fillRoundRect(0,0,495,495,15,15);
    		
    		g.setFill(Color.rgb(172, 147, 132));
            g.setFont(Font.font("Dialog", FontWeight.BOLD, 128));
            g.fillText("2048", 110, 200);
            
            g.setFill(Color.rgb(194, 176, 188));
            g.setFont(Font.font("Dialog", FontWeight.BOLD, 25));
            g.fillText("Click To Start", 170, 300);
            
            
            
    	}else if(gameState == State.running) {
    		g.setFill(Color.rgb(162, 152, 166));
        	g.fillRoundRect(0,0,495,499,15,15);
    		
    		for(int i=0 ; i<4 ; i++) {
    			for(int j=0 ; j<4 ; j++) {
    				if(square[i][j] == null) {
	    				g.setFill(Color.rgb(253, 249, 238));
	    				g.fillRoundRect(15 + i*120, 15+ j*120, 105, 105, 7, 7);
    				} else {
    					drawSquare(g, i, j);
    				}
    			}
    		}
    	}else if(gameState == State.gameOver) {
    		g.setFill(Color.rgb(237,226,219));
        	g.fillRoundRect(0,0,499,499,15,15);
    		
    		g.setFill(Color.rgb(172, 147, 132));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 60));
    		g.fillText("Game Over", 90, 200);
    		
    		
    		
    		g.setFill(Color.rgb(194, 176, 188));
            g.setFont(Font.font("Dialog", FontWeight.BOLD, 25));
            g.fillText("Click To Restart", 150, 400);
    		
    	}else if(gameState == State.win) {
    		g.setFill(Color.rgb(237,226,219));
        	g.fillRoundRect(0,0,499,499,15,15);
    		
    		g.setFill(Color.rgb(172, 147, 132));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 60));
    		g.fillText("You Win!", 120, 200);
    		
    		g.setFill(Color.rgb(113,  95,  80));
    		String smile = "\uD83D\uDE06";
    		g.fillText(smile, 225, 300);
    		
    		g.setFill(Color.rgb(194, 176, 188));
            g.setFont(Font.font("Dialog", FontWeight.BOLD, 25));
            g.fillText("Click To Restart", 150, 400);
    	}
    }
    
    
    private void drawSquare(GraphicsContext g, int i, int j) {
    	int value = square[i][j].getValue();
    	String s = String.valueOf(value);
    	if( value == 2 ) {
			g.setFill(Color.rgb(241, 225, 208));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(117, 117, 117));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 55+i*120, 80+j*120);
    		
    	}else if( value == 4 ) {
			g.setFill(Color.rgb(228, 206, 206));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(117, 117, 117));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 55+i*120, 80+j*120);

    	}else if( value == 8) {
			g.setFill(Color.rgb(201, 192, 211));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(117, 117, 117));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 55+i*120, 80+j*120);

    	}else if( value == 16) {
			g.setFill(Color.rgb(200, 213, 236));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(117, 117, 117));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 45+i*120, 80+j*120);
    	}else if( value == 32) {
			g.setFill(Color.rgb(188, 203, 176));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(117, 117, 117));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 45+i*120, 80+j*120);
    	}else if( value == 64) {
			g.setFill(Color.rgb(175, 213, 220));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(117, 117, 117));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 45+i*120, 80+j*120);
    	}else if( value == 128) {
			g.setFill(Color.rgb(217, 164, 155));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(253, 249, 238));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 33+i*120, 80+j*120);
    	}else if( value == 256) {
			g.setFill(Color.rgb(153, 164, 188));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(253, 249, 238));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 33+i*120, 80+j*120);
    	}else if( value == 512) {
			g.setFill(Color.rgb(156, 158, 137));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(253, 249, 238));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 33+i*120, 80+j*120);
    	}else if( value == 1024) {
    		g.setFill(Color.rgb(227, 140, 122));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(253, 249, 238));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 21+i*120, 80+j*120);
    	}else if( value == 2048) {
    		g.setFill(Color.rgb(255, 255, 255));
    		g.fillRoundRect(15+i*120 ,15+j*120,105,105,7,7);
    		
    		g.setFill(Color.rgb(0, 0, 0));
    		g.setFont(Font.font("Dialog", FontWeight.BOLD, 40));
    		g.fillText(s, 21+i*120, 80+j*120);
    	}
    	
    }
    
    public void randomSquare() {
    	int location = rand.nextInt(16);
    	int x, y;
    	
    	do {
    		location = (location + 1) % 16;
    		x = location / 4;
        	y = location % 4;
    	}while(square[x][y] != null);
    	
    	square[x][y] = new Square(2);
    }
    
    private boolean right() {
    	return move(15,0,1);
    }
    
    private boolean left() {
    	return move(0,0,-1);
    }
    
    private boolean up() {
    	return move(0,-1,0);
    }
    
    private boolean down() {
    	return move(15,1,0);
    }
    
    private boolean move(int v, int addY, int addX) {
    	boolean moved = false;
    	
    	for(int i=0 ; i<16; i++) {
        	int z = Math.abs(v-i);
        	int x = z / 4;
        	int y = z % 4;
        	
        	if(square[x][y] == null) {
        		continue;
        	}
        	
        	int newX = x + addX;
        	int newY = y + addY;
        	
        	while(newX < 4 && newY < 4 && newX >= 0 && newY >= 0) {
            	Square newSquare = square[newX][newY];
        		Square nowSquare = square[x][y];
            	
            	if(newSquare == null) {
            		if(checkMove) {
            			return true;
            		}
                	square[newX][newY] = nowSquare;
            		square[x][y] = null;
                	x = newX;
                	y = newY;
                	newX = newX + addX;
                	newY = newY + addY;
                	moved = true;
                	                	
            	}else if(newSquare.canCombine(nowSquare)) {
            		if(checkMove) {
            			return true;
            		}
            		int value = newSquare.combineTogether(nowSquare);
            		if(value > highest) {
                		highest = value;
                	}
            		square[x][y] = null;
                	moved = true;
                	break;
            	}else {
            		break;
            	}
        	}
    	} 
    	
    	if(moved) {  
    		
    		if (highest < goal) {
            clearCombine();
            randomSquare();
    		}else if (highest == goal) {
                gameState = State.win;
            }
    		
            if (canMove()==false) {
                gameState = State.gameOver;  
            }	
        } 
    	
    	return moved;
    }
		
    private boolean canMove() {
    	checkMove = true;
        boolean hasMove = up() || down() || right() || left();
        checkMove = false;
        return hasMove;
    }
    
    private void clearCombine() {
        for (Square[] row : square) {
            for (Square square : row) {
                if (square != null) {
                    square.setCombine(false);
                }
            }
        }
    }
}
