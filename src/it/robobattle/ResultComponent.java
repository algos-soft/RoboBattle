package it.robobattle;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Componente che rappresenta un risultato in una cella della JTable.
 * Created by alex on 24/06/16.
 */
public class ResultComponent extends JPanel {

    public ResultComponent(TestSessionResult result) {

        setBackground(new Color(204,204,255));


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        if(result!=null) {

            String snum;

            long millis=result.getMillis();
            String stime=Lib.millisToString(millis);
            JLabel labelTime = new JLabel(stime + " sec");

            snum = NumberFormat.getIntegerInstance().format(result.getWordcount());
            JLabel labelIter=new JLabel(snum + " words");

            snum = NumberFormat.getIntegerInstance().format(result.getErrcount());
            JLabel labelErr=new JLabel(snum + " errors");

            int pts = result.getPoints();
            JLabel labelPts=new JLabel(pts + " points", SwingConstants.CENTER);
            labelPts.setBackground(Color.BLUE);
            labelPts.setForeground(Color.WHITE);
            labelPts.setOpaque(true);
            labelPts.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

            add(labelTime);
            add(labelIter);
            add(labelErr);
            add(Box.createRigidArea(new Dimension(6, 6)));
            add(labelPts);

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
