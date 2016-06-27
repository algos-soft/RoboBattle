package it.robobattle;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by alex on 24/06/16.
 */
public class ResultComponent extends JPanel {

    public ResultComponent(TestSessionResult result) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if(result!=null) {

            String snum;

            long millis=result.getMillis();
            String stime=Lib.millisToString(millis);
            JLabel labelTime = new JLabel(stime + " sec");

            snum = NumberFormat.getIntegerInstance().format(result.getWordcount());
            JLabel labelIter=new JLabel(snum + " words");

            snum = NumberFormat.getIntegerInstance().format(result.getErrcount());
            JLabel labelErr=new JLabel(snum + " errors");

            add(labelTime);
            add(labelIter);
            add(labelErr);

            if(result.getErrcount()>0){
                labelErr.setOpaque(true);
                labelErr.setForeground(Color.WHITE);
                labelErr.setBackground(Color.RED);
            }

        }else{
            //add(new JLabel("?"));

        }


    }

}