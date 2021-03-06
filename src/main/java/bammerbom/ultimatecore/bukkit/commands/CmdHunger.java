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
package bammerbom.ultimatecore.bukkit.commands;

import bammerbom.ultimatecore.bukkit.r;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHunger implements UltimateCommand {

    @Override
    public String getName() {
        return "hunger";
    }

    @Override
    public String getPermission() {
        return "uc.hunger";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList();
    }

    @Override
    public void run(final CommandSender cs, String label, String[] args) {
        if (!r.perm(cs, "uc.hunger", false, true)) {
            return;
        }
        if (!r.checkArgs(args, 0)) {
            if (!r.isPlayer(cs)) {
                return;
            }
            r.sendMes(cs, "hungerUsage");
        } else if (r.checkArgs(args, 0) && !r.checkArgs(args, 1)) {
            if (!r.isPlayer(cs)) {
                return;
            }
            if (r.isInt(args[0])) {
                Integer d = Integer.parseInt(args[0]);
                Player p = (Player) cs;
                p.setFoodLevel(p.getFoodLevel() - r.normalize(d, 0, 20));
                r.sendMes(cs, "hungerSelf", "%Player", p.getName(), "%Hunger", args[0]);
            } else {
                r.sendMes(cs, "numberFormat", "%Number", args[0]);
            }
        } else if (r.checkArgs(args, 1)) {
            if (!r.perm(cs, "uc.hunger.others", false, true)) {
                return;
            }
            if (r.isInt(args[0])) {
                Integer d = Integer.parseInt(args[0]);
                Player t = r.searchPlayer(args[1]);
                if (t == null) {
                    r.sendMes(cs, "playerNotFound", "%Player", args[1]);
                    return;
                }
                t.setFoodLevel(t.getFoodLevel() - r.normalize(d, 0, 20));
                r.sendMes(cs, "hungerOthers", "%Player", t.getName(), "%Hunger", args[0]);
            } else if (r.isInt(args[1])) {
                Integer d = Integer.parseInt(args[1]);
                Player t = r.searchPlayer(args[0]);
                if (t == null) {
                    r.sendMes(cs, "playerNotFound", "%Player", args[0]);
                    return;
                }
                t.setFoodLevel(t.getFoodLevel() - r.normalize(d, 0, 20));
                r.sendMes(cs, "hungerOthers", "%Player", t.getName(), "%Hunger", args[1]);
            } else {
                r.sendMes(cs, "numberFormat", "%Number", args[0]);
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
