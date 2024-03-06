package at.sma.game.easygame;

import org.newdawn.slick.*;

import java.util.ArrayList;

public class EasyGame extends BasicGame {

    private Image background;
    private MeinUfo mufo;

    private Crusher crusher;

    private ArrayList<MeinUfo>  mufoList;
    private Sound sound;
    private Music music;
    private int lautstaerke =0;
    private int hit=0;
    private int miss=0;
    private AngelCodeFont font;

    public EasyGame() {
        super("EasyGame");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1024, 768, false);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }



    @Override
    public void init(GameContainer container) throws SlickException {
        font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
        background = new Image("assets/pics/background.png");
        mufo = new MeinUfo(100,100, new Image("assets/pics/meinufo.png"));
        crusher = new Crusher( 512, 700, new Image("assets/pics/crusher.png"),container.getInput());
        mufoList = new ArrayList<MeinUfo>();
        for (int i=0;i<10;i++) {
            mufoList.add(new MeinUfo(100,100, new Image("assets/pics/meinufo.png")));
        }
        music = new Music("testdata/kirby.ogg", true);
        sound = new Sound("testdata/burp.aif");
        music.play();
        music.loop();

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        // Fenster mit ESC sclie?en
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
        if (input.isKeyDown(Input.KEY_2)) {
            lautstaerke = lautstaerke +1;
            if (lautstaerke >=10) lautstaerke =10;
            music.setVolume(lautstaerke/10f);
        }

        if (input.isKeyDown(Input.KEY_1)) {
            lautstaerke = lautstaerke - 1;
            if (lautstaerke < 1) lautstaerke = 0;
            music.setVolume(lautstaerke / 10f);
        }

        for(MeinUfo u : mufoList) {
            if (crusher.intersects((u.getShape()))) {
                System.out.println("KOLLISION");
                sound.play();
                hit ++;
                u.setRandomPosition ();
            }
            if (u.getY() > 768) {
                miss++;
                u.setRandomPosition ();
            }
            u.update(delta);
        }
        crusher.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        mufo.draw(g);
        crusher.draw(g);
        font.drawString(80, 5, "hit"+hit, Color.green);
        font.drawString(80, 25, "miss"+miss, Color.red);
        for(MeinUfo u : mufoList) {
            u.draw(g);

        }
    }




}
