package com.societe.blog.domain;

public enum ROOM {

    OUESSANT("Ouessant"),
    MOLENE("Molène"),
    GROIX("Groix"),
    BELLEILEENMER("Belle-Île-en-Mer"),
    BREHAT("Bréhat");

    private String libelle;

    ROOM(String s) {
        this.libelle = s;
    }

    public static ROOM fromString(String libelle) {
        if (libelle != null) {
            for (ROOM f : ROOM.values()) {
                if (f.libelle.equalsIgnoreCase(libelle)) {
                    return f;
                }
            }
        }
        return null;
    }
}
