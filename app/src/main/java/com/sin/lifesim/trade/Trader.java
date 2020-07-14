package com.sin.lifesim.trade;

import com.sin.lifesim.Krmic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ConstantConditions")
public class Trader {
    int relation;
    String[] items;
    int[] prices;

    public Trader(Trade.items relation, @Nullable String[] items, @Nullable int[] prices) {
        this.relation = relation.relation;
        if (items != null) {
            this.items = items;
        } else {
            this.items = itemsGenerate();
        }
        if (prices != null) {
            this.prices = prices;
        } else this.prices = priceGenerate();
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

    public static String[] itemsGenerate(@NotNull HashMap<String, Integer> hashMap) {
        ArrayList<String> ret = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            for (int i = hashMap.get(s); i == 0; i--) {
                Scanner scnr = new Scanner(Trade.items.ITEMS_TRADE);
                while (scnr.hasNext()) {
                    if (scnr.next().equals(s)) {
                        ret.add(s);
                    }
                }
            }
        }
        return Krmic.poleConverter(Krmic.polepull(ret));
    }

    private int[] priceGenerate() {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = items.length; i != 0; i--) {
            switch (relation) {
                case 0:
                    ret.add(ThreadLocalRandom.current().nextInt(15, 30));
                case 1:
                    ret.add(ThreadLocalRandom.current().nextInt(5, 15));
                case -1:
                    ret.add(ThreadLocalRandom.current().nextInt(30, 50));

            }
        }
        Krmic k = new Krmic();
        return k.poleConverter(Krmic.poleConverter(Krmic.polepull(ret)));
    }

}
