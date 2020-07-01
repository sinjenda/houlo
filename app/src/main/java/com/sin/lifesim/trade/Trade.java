package com.sin.lifesim.trade;

import com.sin.lifesim.MainActivity;

public class Trade {
    MainActivity m;

    public static class items {
        public static int HOSTILE = -1;
        public static int NEUTRAL = 0;
        public static int FRIEND = 1;
        public static String ITEMS_TRADE = "apple banana pancakes knife axe medicine";
        int relation;

        public items(int relation) {
            this.relation = relation;
        }
    }

    public Trade(MainActivity m) {
        this.m = m;
    }

    public static void trade(Trader trader) {

    }
}
