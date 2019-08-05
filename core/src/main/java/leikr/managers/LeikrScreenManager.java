package leikr.managers;

import leikr.GameRuntime;
import leikr.loaders.ImageLoader;
import leikr.loaders.MapLoader;
import leikr.loaders.SpriteLoader;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.Graphics;
import org.mini2Dx.core.graphics.Color;
import org.mini2Dx.core.graphics.Colors;
import org.mini2Dx.core.graphics.Sprite;

/**
 * This class is used to manage the drawing API for the Engine. It also provides
 * a screen instance which can be passed to non Engine extending classes in the
 * game code.
 *
 * @author tor
 */
public class LeikrScreenManager {

    /*
     * Loaders
     *
     * The loaders are used to load the custom assets for a game at startup.
     */
    SpriteLoader spriteLoader;
    ImageLoader imageLoader;
    MapLoader mapLoader;

    Graphics g;

    private static LeikrScreenManager instance;

    /*
     * Properties set by Custom Properties
     *
     * These can be overwritten for a more custom experience.
     */
    private int MAX_SPRITES;
    private int USED_SPRITES;

    /**
     * LeikrScreenManager constructor
     *
     * @param mSprites
     */
    private LeikrScreenManager() {
    }

    public static LeikrScreenManager getLeikrScreenManager(int mSprites) {
        if (instance == null) {
            instance = new LeikrScreenManager();
        }
        instance.resetLeikrScreenManager(mSprites);
        return instance;
    }

    private void resetLeikrScreenManager(int mSprites) {
        MAX_SPRITES = mSprites;
        spriteLoader = SpriteLoader.getSpriteLoader();
        imageLoader = ImageLoader.getImageLoader();
        mapLoader = MapLoader.getMapLoader();
    }


    //Helper methods
    public int getUsedSprites() {
        return USED_SPRITES;
    }
    //End helper methods

    //Engine methods
    public void preRender(Graphics g) {
        this.g = g;
        //set to 0 before drawing anything
        USED_SPRITES = 0;
    }

    public void preUpdate(float delta) {
        if (null == mapLoader.getMap()) {
            return;//don't update the map if it is null
        }
        mapLoader.getMap().update(delta);
    }

    public void dispose() {
        spriteLoader.disposeSprites();
        imageLoader.disposeImages();
        mapLoader.disposeMap();
    }
    //End Engine methods

    //Image methods
    public final void loadImages() {
        imageLoader.load();
    }

    public final void image(String name, float x, float y) {
        g.drawTexture(imageLoader.getImage(name), x, y);
    }

    public final void image(String name, float x, float y, float w, float h) {
        g.drawTexture(imageLoader.getImage(name), x, y, w, h);
    }

    public final void image(String name, float x, float y, float w, float h, boolean flipv) {
        g.drawTexture(imageLoader.getImage(name), x, y, w, h, flipv);
    }
    //end Image methods

    //Map methods
    public final void loadMap(String map) {
        mapLoader.loadMap(map);
    }

    public final void map() {
        mapLoader.drawMap(g);
    }

    public final void map(int x, int y) {
        mapLoader.drawMap(g, x, y);
    }

    public final void map(int x, int y, int layer) {
        mapLoader.drawMap(g, x, y, layer);
    }

    public final void map(int x, int y, int sx, int sy, int w, int h) {
        mapLoader.drawMap(g, x, y, sx, sy, w, h);
    }

    public final void map(int x, int y, int sx, int sy, int w, int h, int layer) {
        mapLoader.drawMap(g, x, y, sx, sy, w, h, layer);
    }

    public final int mapGet(int x, int y) {
        return mapLoader.getMapTileId(x, y);
    }

    public final void mapSet(int x, int y, int id) {
        mapLoader.setMapTile(x, y, id);
    }

    public final void mapRemove(int x, int y) {
        mapLoader.removeMapTile(x, y);
    }

    public final int getMapHeight() {
        return mapLoader.getMap().getHeight();
    }

    public final int getMapWidth() {
        return mapLoader.getMap().getWidth();
    }
    //end Map methods

    //start color methods
    public final Color getDrawColor(int color) {
        switch (color) {
            case 0:
                return Colors.CLEAR();
            case 1:
                return Colors.WHITE();
            case 2:
                return Colors.WHITE_M1();
            case 3:
                return Colors.LIGHT_GRAY();
            case 4:
                return Colors.GRAY();
            case 5:
                return Colors.DARK_GRAY();
            case 6:
                return Colors.BLACK_P1();
            case 7:
                return Colors.BLACK();
            case 8:
                return Colors.RED();
            case 9:
                return Colors.GREEN();
            case 10:
                return Colors.BLUE();
            case 11:
                return Colors.MAROON();
            case 12:
                return Colors.CORAL();
            case 13:
                return Colors.SALMON();
            case 14:
                return Colors.PINK();
            case 15:
                return Colors.LIME();
            case 16:
                return Colors.FOREST();
            case 17:
                return Colors.OLIVE();
            case 18:
                return Colors.NAVY();
            case 19:
                return Colors.ROYAL();
            case 20:
                return Colors.SKY();
            case 21:
                return Colors.CYAN();
            case 22:
                return Colors.TEAL();
            case 23:
                return Colors.YELLOW();
            case 24:
                return Colors.GOLD();
            case 25:
                return Colors.GOLDENROD();
            case 26:
                return Colors.ORANGE();
            case 27:
                return Colors.BROWN();
            case 28:
                return Colors.TAN();
            case 29:
                return Colors.FIREBRICK();
            case 30:
                return Colors.PURPLE();
            case 31:
                return Colors.VIOLET();
            case 32:
                return Colors.MAGENTA();
            default:
                return Colors.BLACK();
        }
    }

    public final void drawColor(int color) {
        g.setColor(getDrawColor(color));
    }

    public final void drawColor(String c) {
        g.setColor(Colors.rgbToColor(c));
    }

    public final void drawColor(String c, boolean alpha) {
        if (alpha) {
            g.setColor(Colors.rgbaToColor(c));
        } else {
            g.setColor(Colors.rgbToColor(c));
        }
    }

    public final void bgColor(int color) {
        drawColor(color);
        g.fillRect(-1, -1, GameRuntime.WIDTH + 1, GameRuntime.HEIGHT + 1);
    }

    public final void bgColor(String c) {
        drawColor(c);
        g.fillRect(-1, -1, GameRuntime.WIDTH + 1, GameRuntime.HEIGHT + 1);
    }

    //end color methods

    //text methods
    public final void text(String text, float x, float y, int color) {
        drawColor(color);
        g.drawString(text, x, y);
    }

    public final void text(String text, float x, float y, String color) {
        drawColor(color);
        g.drawString(text, x, y);
    }

    public final void text(String text, float x, float y, float width, int color) {
        drawColor(color);
        g.drawString(text, x, y, width);
    }

    public final void text(String text, float x, float y, float width, String color) {
        drawColor(color);
        g.drawString(text, x, y, width);
    }

    public final void text(String text, float x, float y, float width, int align, int color) {
        drawColor(color);
        g.drawString(text, x, y, width, align);
    }

    public final void text(String text, float x, float y, float width, int align, String color) {
        drawColor(color);
        g.drawString(text, x, y, width, align);
    }
    //end text methods

    //sprite helper methods.
    private void drawSpriteRotate(int id, int size, float degr, float x, float y) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        Sprite t = spriteLoader.getSprite(id, size);
        t.rotate(degr);
        g.drawSprite(t, x, y);
        t.rotate(-degr);
        USED_SPRITES++;
    }

    private void drawSpriteFlip(int id, float x, float y, int size, boolean flipX, boolean flipY) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        Sprite t = spriteLoader.getSprite(id, size);
        t.setFlip(flipX, flipY);
        g.drawSprite(t, x, y);
        t.setFlip(false, false);
        USED_SPRITES++;
    }

    //start 8x8 sprites. Default size 0 (8x8)
    public final void sprite(int id, float x, float y) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        g.drawSprite(spriteLoader.getSprite(id, 0), x, y);
        USED_SPRITES++;
    }

    public final void sprite(int id, float x, float y, float degr) {
        drawSpriteRotate(id, 0, degr, x, y);
    }

    public final void sprite(int id, float x, float y, boolean flipX, boolean flipY) {
        drawSpriteFlip(id, x, y, 0, flipX, flipY);
    }
    //end 8x8 sprites

    //start sizable sprites
    public final void sprite(int id, float x, float y, int size) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        g.drawSprite(spriteLoader.getSprite(id, size), x, y);
        USED_SPRITES++;
    }

    public final void sprite(int id, float x, float y, float degr, int size) {
        drawSpriteRotate(id, size, degr, x, y);
    }

    public final void sprite(int id, float x, float y, boolean flipX, boolean flipY, int size) {
        drawSpriteFlip(id, x, y, size, flipX, flipY);
    }
    //end sizable sprites

    //START special sprite mode
    public final void spriteSc(int id, float x, float y, float scale) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        Sprite t = spriteLoader.getSprite(id, 0);
        t.scale(scale);
        g.drawSprite(t, x, y);
        t.scale(-scale);
        USED_SPRITES++;
    }

    public final void spriteSc(int id, float x, float y, float scaleX, float scaleY) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        Sprite t = spriteLoader.getSprite(id, 0);
        t.setScale(scaleX, scaleY);
        g.drawSprite(t, x, y);
        t.setScale(-scaleX, -scaleY);
        USED_SPRITES++;
    }

    public final void spriteSc(int id, float x, float y, float scaleX, float scaleY, float degr) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        Sprite t = spriteLoader.getSprite(id, 0);
        t.setScale(scaleX, scaleY);
        t.rotate(degr);
        g.drawSprite(t, x, y);
        t.rotate(-degr);
        t.setScale(-scaleX, -scaleY);
        USED_SPRITES++;
    }

    public final void spriteSc(int id, float x, float y, float scaleX, float scaleY, boolean flipX, boolean flipY) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        Sprite t = spriteLoader.getSprite(id, 0);
        t.setScale(scaleX, scaleY);
        t.flip(flipX, flipY);
        g.drawSprite(t, x, y);
        t.flip(!flipX, !flipY);
        t.setScale(-scaleX, -scaleY);
        USED_SPRITES++;
    }
    //END special sprite mode

    //START animated sprites
    public Animation makeAnimSprite(int[] ids, float[] length) {
        Animation<Sprite> tmp = new Animation<>();
        for (int i = 0; i < ids.length; i++) {
            tmp.addFrame((Sprite) spriteLoader.getSprite(ids[i], 0), length[i]);
        }
        return tmp;
    }

    public Animation makeAnimSprite(int[] ids, float[] length, boolean loop) {
        Animation<Sprite> tmp = new Animation<>();
        for (int i = 0; i < ids.length; i++) {
            tmp.addFrame((Sprite) spriteLoader.getSprite(ids[i], 0), length[i]);
        }
        tmp.setLooping(loop);
        return tmp;
    }

    public Animation makeAnimSprite(int[] ids, float[] length, int size) {
        Animation<Sprite> tmp = new Animation<>();
        for (int i = 0; i < ids.length; i++) {
            tmp.addFrame((Sprite) spriteLoader.getSprite(ids[i], size), length[i]);
        }
        return tmp;
    }

    public Animation makeAnimSprite(int[] ids, float[] length, int size, boolean loop) {
        Animation<Sprite> tmp = new Animation<>();
        for (int i = 0; i < ids.length; i++) {
            tmp.addFrame((Sprite) spriteLoader.getSprite(ids[i], size), length[i]);
        }
        tmp.setLooping(loop);
        return tmp;
    }

    public void spriteAnim(Animation sprite, float x, float y) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        sprite.draw(g, x, y);
    }

    public void spriteAnim(Animation sprite, float x, float y, boolean flipH, boolean flipV) {
        if (USED_SPRITES >= MAX_SPRITES) {
            return;
        }
        sprite.flip(flipH, flipV);
        sprite.draw(g, x, y);
        sprite.flip(!flipH, !flipV);
    }
    //END animated sprites

    //start shape drawing methods

    public final void rect(int x, int y, int w, int h) {
        g.drawRect(x, y, w, h);
    }

    public final void rect(int x, int y, int w, int h, boolean fill) {
        if (fill) {
            g.fillRect(x, y, w, h);
        } else {
            g.drawRect(x, y, w, h);
        }
    }

    public final void circle(int x, int y, int r) {
        g.drawCircle(x, y, r);
    }

    public final void circle(int x, int y, int r, boolean fill) {
        if (fill) {
            g.fillCircle(x, y, r);
        } else {
            g.drawCircle(x, y, r);
        }
    }

    public final void triangle(int x, int y, int x2, int y2, int x3, int y3) {
        g.drawTriangle(x, y, x2, y2, x3, y3);
    }

    public final void triangle(int x, int y, int x2, int y2, int x3, int y3, boolean fill) {
        if (fill) {
            g.fillTriangle(x, y, x2, y2, x3, y3);
        } else {
            g.drawTriangle(x, y, x2, y2, x3, y3);
        }
    }

    public final void line(int x, int y, int x2, int y2) {
        g.drawLineSegment(x, y, x2, y2);
    }
    //end shape drawing methods

    //EXPERIMENTAL METHODS
    public final void clip(float x, float y, float w, float h) {
        g.setClip(x, y, w, h);
    }

    public final void clip() {
        g.removeClip();
    }

    //END EXPERIMENTAL
}
