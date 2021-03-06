/*
 * This file is part of UltimateCore, licensed under the MIT License (MIT).
 *
 * Copyright (c) Bammerbom
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
package bammerbom.ultimatecore.spongeapi.commands;

import bammerbom.ultimatecore.spongeapi.r;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdSave implements UltimateCommand {

    Boolean autosaveMessage = r.getCnfg().getBoolean("Command.Save.autosaveMessage");

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getPermission() {
        return "uc.save";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList();
    }

    @Override
    public void run(final CommandSender cs, String label, String[] args) {
        if (!r.perm(cs, "uc.save", false, true)) {
            return;
        }
        if (r.checkArgs(args, 0)) {
            World w = Bukkit.getWorld(args[0]);
            if (w == null) {
                r.sendMes(cs, "worldNotFound", "%World", args[0]);
                return;
            }
            if (autosaveMessage) {
                Bukkit.broadcastMessage(r.mes("saveStart"));
            } else {
                r.sendMes(cs, "saveStart");
            }
            w.save();
        } else {
            if (autosaveMessage) {
                Bukkit.broadcastMessage(r.mes("saveStart"));
            } else {
                r.sendMes(cs, "saveStart");
            }
            for (World w : Bukkit.getWorlds()) {
                w.save();
            }
        }
        if (autosaveMessage) {
            Bukkit.broadcastMessage(r.mes("saveFinish"));
        } else {
            r.sendMes(cs, "saveFinish");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
