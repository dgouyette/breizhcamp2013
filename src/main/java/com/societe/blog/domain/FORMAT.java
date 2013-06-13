package com.societe.blog.domain;

public enum FORMAT {
    QUICKIE("Quickie"),
    HANDSON("hands-on"),
    CONFERENCE("Conference"),
    BIGLAB("Biglab"),
    LAB("lab"),
    UNIVERSITE("universite"),
    TOOLS_IN_ACTION("tools in action"),
    KEYNOTE("keynote");

    private String libelle;

    FORMAT(String s) {
        this.libelle = s;
    }

    public static FORMAT fromString(String libelle) {
        if (libelle != null) {
            for (FORMAT f : FORMAT.values()) {
                if (f.libelle.equalsIgnoreCase(libelle)) {
                    return f;
                }
            }
        }
        return null;
    }

}
