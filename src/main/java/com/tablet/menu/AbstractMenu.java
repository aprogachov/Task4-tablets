package com.tablet.menu;

import com.tablet.menu.util.MenuHelper;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class AbstractMenu implements Runnable {

    private final List<IMenuItem> topLevelItems;
    private final MenuHelper menuHelper;
    private Map<Integer, IMenuItem> menuItems;
    private String helpData;

    public AbstractMenu(List<IMenuItem> topLevelItems, MenuHelper menuHelper) {
        this.topLevelItems = topLevelItems;
        this.menuHelper = menuHelper;
    }

    @PostConstruct
    public void init() {
        AtomicInteger counter = new AtomicInteger(1);

        menuItems = topLevelItems.stream()
                .sorted(Comparator.comparing(IMenuItem::priority))
                .collect(Collectors.toMap(o -> counter.getAndIncrement(), o -> o));

        menuItems.put(counter.getAndIncrement(), new IMenuItem() {
            @Override
            public String getTitle() {
                return "Exit";
            }

            @Override
            public int doAction() {
                return IMenuItem.EXIT_CODE;
            }
        });


        helpData = menuItems.entrySet()
                .stream()
                .map(e -> e.getKey() + ": " + e.getValue().getTitle())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void run() {
        int lastCode = 0;
        while (lastCode != IMenuItem.EXIT_CODE) {
            System.out.println(helpData);
            int item = getItem();
            IMenuItem menuItem = menuItems.get(item);
            lastCode = menuItem.doAction();
        }
    }

    private int getItem() {
        return menuHelper.readPosition(menuItems.size());
    }
}
