package com.sin.lifesim.trade;

import com.sin.lifesim.Item.Item;
import com.sin.lifesim.Krmic;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Trader {
    final int relation;
    final ArrayList<Item> items;
    final int[] prices;

    public Trader(Trade.items relation, @Nullable ArrayList<Item> items, @Nullable int[] prices) {
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

    private ArrayList<Item> itemsGenerate() {
        ArrayList<Item> ret = new ArrayList<>();
        int bound = Trade.items.ITEMS_TRADE.length;
        for (int i = ThreadLocalRandom.current().nextInt(2, Trade.items.ITEMS_TRADE.length); i != 0; i--) {
            ret.add(Trade.items.ITEMS_TRADE[ThreadLocalRandom.current().nextInt(0, bound)]);
        }
        return ret;
    }


    private int[] priceGenerate() {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = items.size(); i != 0; i--) {
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
        return Krmic.poleConverter(Krmic.poleConverter(Krmic.polepull(ret)));
    }

}
