package org.kristallpojken.tangram1;

/**
 * Spelet har sex rutor: Tom, ifylld och halvifylld roterad i 90-graderssteg
 */
public enum Tiles {
    _empty(0,"︎◻︎"),_90deg(1,"◥"),_180deg(2,"◢"),_270deg(3,"◣"),_360deg(4,"◤"),_full(5,"█");
    private int nr;
    private String text;
    Tiles(int nr, String text)
    {
        this.nr=nr;
        this.text=text;
    }
    public int nr()
    {
        return nr;
    }
    public String text()
    {
        return text;
    }
    public String toString()
    {
        return "Ruta med nr "+nr+": "+text;
    }
}
