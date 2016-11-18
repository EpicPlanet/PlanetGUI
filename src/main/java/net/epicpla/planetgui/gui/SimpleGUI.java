/*
 * This file is part of PlanetGUI, licensed under the MIT License (MIT).
 *
 * Copyright (c) Epic Planet Minecraft Server <https://epicpla.net>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.epicpla.planetgui.gui;

import net.epicpla.planetgui.PlanetGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class SimpleGUI implements GUI {

    private final Player player;
    private final InventoryView view;
    private boolean open;
    private boolean locked = false;

    public SimpleGUI(Player player, int size) {
        this.player = player;
        Inventory inventory = Bukkit.createInventory(null, size);
        render();
        view = player.openInventory(inventory);
        open = true;
        PlanetGUI.setGUI(player, this);
    }

    public abstract void render();

    public void reOpen() {
        getPlayer().openInventory(getView());
    }

    public void close() {
        if (open) {
            getPlayer().closeInventory();
            open = false;
        }
    }

    public void scheduleClose() {
        if (open) {
            setLocked(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    close();
                }
            }.runTaskLater(PlanetGUI.getInstance(), 0);
            getPlayer().closeInventory();
            open = false;
        }
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    public InventoryView getView() {
        return view;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (isLocked()) {
            event.setCancelled(true);
        } else {
            handleClick(event);
        }
    }

    @Override
    public void onDrag(InventoryDragEvent event) {
        if (isLocked()) {
            event.setCancelled(true);
        } else {
            handleDrag(event);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        if (isLocked()) {
            reOpen();
        } else {
            handleClose(event);
        }
    }

    public abstract void handleClick(InventoryClickEvent event);
    public abstract void handleDrag(InventoryDragEvent event);
    public abstract void handleClose(InventoryCloseEvent event);

    @Override
    public void onEvent(Event event) {

    }

    @Override
    public void forceClose() {
        close();
    }
}
