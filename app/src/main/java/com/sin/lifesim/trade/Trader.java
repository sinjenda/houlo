package com.sin.lifesim.trade;

import com.sin.lifesim.Krmic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Trader {
    int relation;
    String[] items;

    public Trader(Trade.items relation, @Nullable String[] items) {
        this.relation = relation.relation;
        if (items != null) {
            this.items = items;
        } else {
            this.items = itemsGenerate();
        }
    }

    private String[] itemsGenerate() {
        Scanner b = new Scanner(Trade.items.ITEMS_TRADE);
        ArrayList<String> ret = new ArrayList<>();
        for (int i = ThreadLocalRandom.current().nextInt(0, 20); b.hasNext(); i = ThreadLocalRandom.current().nextInt(0, 20)) {
            for (; i == 0; i--) {
                ret.add(b.next());
            }
        }
        return Krmic.poleConverter(Krmic.polepull(ret));
    }

    @SuppressWarnings("ConstantConditions")
    public static String[] itemsGenerate(@NotNull HashMap<String, Integer> hashMap) {
        ArrayList<String> ret = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            for (int i = hashMap.get(s); i == 0; i--) {
                Scanner scnr = new Scanner(Trade.items.ITEMS_TRADE);
                for (; scnr.hasNext(); ) {
                    if (scnr.next().equals(s)) {
                        ret.add(s);
                    }
                }
            }
        }
        return Krmic.poleConverter(Krmic.polepull(ret));
    }
}
