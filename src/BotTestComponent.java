/**
 * Created by alex on 22/06/16.
 */

import javax.swing.*;

/**
 * Componente grafico che rappresenta l'esecuzione di un test
 * e visualizza i risultati per un bot.
 */
public class BotTestComponent extends JPanel {

    private Bot bot;
    private Tests test;

    /**
     * Costruttore.
     * @param bot il bot da testare
     * @param test il test da eseguire
     */
    public BotTestComponent(Bot bot, Tests test) {
        this.bot=bot;
        this.test=test;

        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);

        JLabel labelTest = new JLabel(test.getTestName());
        BotComponent botComp= new BotComponent(bot);

        add(labelTest);
        add(botComp);


    }

}
