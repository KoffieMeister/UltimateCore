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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetlevel implements UltimateCommand {

    @Override
    public String getName() {
        return "setlevel";
    }

    @Override
    public String getPermission() {
        return "uc.setlevel";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("setlvl");
    }

    @Override
    public void run(final CommandSender cs, String label, String[] args) {
        if (!r.checkArgs(args, 0)) {
            r.sendMes(cs, "setlevelUsage");
            return;
        }
        if (!r.isInt(args[0])) {
            r.sendMes(cs, "numberFormat", "%Number", args[0]);
        }
        Integer i = Integer.parseInt(args[0]);
        Player t;
        if (r.checkArgs(args, 1)) {
            t = Bukkit.getPlayer(args[1]);
            if (t == null) {
                r.sendMes(cs, "playerNotFound", "%Player", args[1]);
                return;
            }
        } else if (r.isPlayer(cs)) {
            t = (Player) cs;
        } else {
            return;
        }
        t.setLevel(i);
        r.sendMes(cs, "experienceSet", "%Settype", r.mes("experienceSettypeLevels"), "%Player", t.getName(), "%Experience", i);
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        if (curn == 1) {
            return null;
        }
        return new ArrayList<>();
    }
}
