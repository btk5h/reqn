/*
 * MIT License
 *
 * Copyright (c) 2016 Bryan Terce
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.w00tmast3r.reqn;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public final class Reqn extends JavaPlugin {
  
  private static Reqn instance;
  private static SkriptAddon addonInstance;
  
  public Reqn() {
    if (instance == null) {
      instance = this;
    } else {
      throw new IllegalStateException();
    }
  }
  
  @Override
  public void onEnable() {
    try {
      getAddonInstance().loadClasses("com.w00tmast3r.reqn", "skript");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static SkriptAddon getAddonInstance() {
    if (addonInstance == null) {
      addonInstance = Skript.registerAddon(getInstance());
    }
    return addonInstance;
  }
  
  public static Reqn getInstance() {
    if (instance == null) {
      throw new IllegalStateException();
    }
    return instance;
  }
}
