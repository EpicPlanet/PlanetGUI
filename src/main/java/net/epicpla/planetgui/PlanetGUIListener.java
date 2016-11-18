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

package net.epicpla.planetgui;

import net.epicpla.planetgui.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.*;

public class PlanetGUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        GUI gui = PlanetGUI.getGUI(player);
        if (gui != null) {
            gui.onClick(event);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        GUI gui = PlanetGUI.getGUI(player);
        if (gui != null) {
            gui.onDrag(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        GUI gui = PlanetGUI.getGUI(player);
        if (gui != null) {
            gui.onClose(event);
        }
    }

    @EventHandler
    public void onBrew(BrewEvent event) {
        event.getContents().getViewers().forEach(player -> {
            GUI gui = PlanetGUI.getGUI((Player) player);
            if (gui != null) {
                gui.onEvent(event);
            }
        });
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();
        GUI gui = PlanetGUI.getGUI(player);
        if (gui != null) {
            gui.onEvent(event);
        }
    }

    @EventHandler
    public void onExtract(FurnaceExtractEvent event) {
        Player player = event.getPlayer();
        GUI gui = PlanetGUI.getGUI(player);
        if (gui != null) {
            gui.onEvent(event);
        }
    }

    @EventHandler
    public void onCreative(InventoryCreativeEvent event) {
        Player player = (Player) event.getWhoClicked();
        GUI gui = PlanetGUI.getGUI(player);
        if (gui != null) {
            gui.onEvent(event);
        }
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        event.getViewers().forEach(player -> {
            GUI gui = PlanetGUI.getGUI((Player) player);
            if (gui != null) {
                gui.onEvent(event);
            }
        });
    }

    @EventHandler
    public void onPrepareAnvil(PrepareItemCraftEvent event) {
        event.getViewers().forEach(player -> {
            GUI gui = PlanetGUI.getGUI((Player) player);
            if (gui != null) {
                gui.onEvent(event);
            }
        });
    }

    @EventHandler
    public void onPrepareAnvil(PrepareItemEnchantEvent event) {
        Player player = event.getEnchanter();
        GUI gui = PlanetGUI.getGUI(player);
        if (gui != null) {
            gui.onEvent(event);
        }
    }



    //FurnaceBurnEvent, FurnaceExtractEvent, FurnaceSmeltEvent, InventoryCreativeEvent,PrepareAnvilEvent, PrepareItemCraftEvent


}
