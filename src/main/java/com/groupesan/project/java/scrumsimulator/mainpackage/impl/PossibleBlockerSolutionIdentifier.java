package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;

public class PossibleBlockerSolutionIdentifier extends ScrumIdentifier {
    public PossibleBlockerSolutionIdentifier(int value) {
        super(value);
    }

    public String toString() {
        return "PBS" + this.id;
    }
}
