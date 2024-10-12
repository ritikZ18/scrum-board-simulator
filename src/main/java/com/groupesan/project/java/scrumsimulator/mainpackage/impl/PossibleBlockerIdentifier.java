package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;

public class PossibleBlockerIdentifier extends ScrumIdentifier {
    public PossibleBlockerIdentifier(int value) {
        super(value);
    }

    public String toString() {
        return "PB" + this.id;
    }
}
