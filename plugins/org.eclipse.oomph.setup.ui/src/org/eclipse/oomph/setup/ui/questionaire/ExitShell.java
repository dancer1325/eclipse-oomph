/*
 * Copyright (c) 2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.oomph.setup.ui.questionaire;

import org.eclipse.oomph.setup.ui.questionaire.AnimatedCanvas.Animator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Eike Stepper
 */
public class ExitShell extends AnimatedShell<Boolean>
{
  private static final int WIDTH = 550;

  private static final int HEIGHT = 450;

  public ExitShell(Shell parent)
  {
    super(parent, SWT.APPLICATION_MODAL);

    Rectangle bounds = parent.getBounds();
    setLocation(bounds.x + (bounds.width - WIDTH) / 2, bounds.y + (bounds.height - HEIGHT) / 2);
    setSize(WIDTH, HEIGHT);
  }

  @Override
  protected void init()
  {
    super.init();

    ExitAnimator animator = new ExitAnimator(getDisplay());
    getCanvas().addAnimator(animator);
  }

  /**
   * @author Eike Stepper
   */
  public static class ExitAnimator extends Animator
  {
    public static final int NONE = -1;

    private static final int BORDER = 30;

    private static final int BIG_FONT_PX = GearAnimator.BIG_FONT_PX;

    private static final int NORMAL_FONT_PX = 20;

    private static Color DARK_GRAY;

    private boolean oldShowOverlay;

    private Font bigFont;

    private Font hoverFont;

    private Font normalFont;

    private Color purple;

    private Image image;

    private Image image_ovr;

    private Rectangle[] boxes = new Rectangle[2];

    private int hover = NONE;

    private int oldHover = NONE;

    private int choice = NONE;

    public ExitAnimator(Display display)
    {
      super(display);
      DARK_GRAY = display.getSystemColor(SWT.COLOR_DARK_GRAY);
    }

    public final int getChoice()
    {
      return choice;
    }

    @Override
    protected void init()
    {
      super.init();
      bigFont = createFont(BIG_FONT_PX);
      hoverFont = createFont(BIG_FONT_PX + 6);
      normalFont = createFont(NORMAL_FONT_PX);

      purple = createColor(43, 34, 84);

      image = loadImage("questionaire/exit_page.png");
      image_ovr = loadImage("questionaire/exit_page_ovr.png");
    }

    @Override
    protected boolean onKeyPressed(KeyEvent e)
    {
      if (e.character == 13)
      {
        finish(true);
        return true;
      }

      if (e.keyCode == SWT.ESC)
      {
        finish(false);
        return true;
      }

      return super.onKeyPressed(e);
    }

    @Override
    protected boolean onMouseMove(int x, int y)
    {
      hover = getAnswer(x, y);
      if (hover != NONE)
      {
        return true;
      }

      return super.onMouseMove(x, y);
    }

    @Override
    protected boolean onMouseDown(int x, int y)
    {
      choice = getAnswer(x, y);
      if (choice != NONE)
      {
        finish(choice == 0);
        return true;
      }

      return super.onMouseDown(x, y);
    }

    private void finish(boolean exit)
    {
      ExitShell shell = (ExitShell)getCanvas().getShell();
      shell.setResult(exit);
      shell.dispose();
    }

    @Override
    protected boolean advance()
    {
      if (hover != oldHover)
      {
        return true;
      }

      return showOverlay() != oldShowOverlay;
    }

    @Override
    protected void paint(GC gc, Image buffer)
    {
      int cX = WIDTH / 2;

      gc.setForeground(purple);
      gc.setFont(bigFont);
      drawText(gc, cX, BORDER + NORMAL_FONT_PX, "Exit Questionnaire?");

      gc.setForeground(DARK_GRAY);
      gc.setFont(normalFont);
      drawText(gc, cX, 2 * BORDER + BIG_FONT_PX, "This one-time questionnaire will not pop up anymore by itself.");
      drawText(gc, cX, 2 * BORDER + BIG_FONT_PX + NORMAL_FONT_PX, "You can restart it later through the Oomph Setup preferences:");

      int x = cX - image.getBounds().width / 2;
      int y = 2 * BORDER + BIG_FONT_PX + 2 * NORMAL_FONT_PX;
      gc.drawImage(image, x, y);

      oldShowOverlay = showOverlay();
      if (oldShowOverlay)
      {
        gc.drawImage(image_ovr, x + 133, y + 105);
      }

      int answerY = HEIGHT - BORDER - NORMAL_FONT_PX;
      gc.setForeground(purple);

      gc.setFont(hover == 0 ? hoverFont : bigFont);
      boxes[0] = drawText(gc, cX - 3 * BORDER, answerY, "Exit Now");

      gc.setFont(hover == 1 ? hoverFont : bigFont);
      boxes[1] = drawText(gc, cX + 3 * BORDER, answerY, "Go Back");

      oldHover = hover;
    }

    private int getAnswer(int x, int y)
    {
      for (int i = 0; i < boxes.length; i++)
      {
        Rectangle box = boxes[i];
        if (box != null && box.contains(x, y))
        {
          return i;
        }
      }

      return NONE;
    }

    private static boolean showOverlay()
    {
      return (System.currentTimeMillis() / 500 & 1) == 1;
    }
  }
}
