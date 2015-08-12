import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by bak on 12/08/2015.
 */
interface Press extends MouseListener {
    @Override
    public default void mouseEntered(MouseEvent e) {}
    @Override
    public default void mouseExited(MouseEvent e) {}
    @Override
    public default void mouseClicked(MouseEvent e) {}
    @Override
    public default void mouseReleased(MouseEvent e) {}
}