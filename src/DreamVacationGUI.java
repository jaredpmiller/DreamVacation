import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DreamVacationGUI{

    /*** Complete and submit this file in AutoLab ***/

    /*** This class contains a large number of small objectives to guide you through a larger program ***/


    /**
     * The drop down menu containing all the destinations from the API allowing user to select a destination.
     * The destination selected by comboBox will referred to as the "selected destination"
     */
    private JComboBox<Destination> comboBox;

    /**
     * A spinner to choose the zoom level of the map tile. The integer selected by spinner will be referred to
     * as the "zoom level"
     */
    private JSpinner spinner;

    /**
     * The label that will contain the map tile as an image retrieved from an API
     */
    private JLabel mapLabel;

    /**
     * A text area containing the chat for the selected destination
     */
    private JTextArea chatArea;

    /**
     * A text area containing the description of the selected destination
     */
    private JTextArea descriptionArea;

    /**
     * The tile server used to retrieve map tiles
     */
    private TileServer tileServer;

    /**
     * Constructor for the GUI. This constructor is called in Main and used to assemble the JFrame.
     * Run Main for testing
     */
    public DreamVacationGUI(){
        initialize();
    }

    /**
     * Sets up all the member variables when the constructor is called. This method is only called once.
     */
    private void initialize(){

        // 3 points
        // Set the instance variable comboBox to a new JComboBox and add to it all the destinations from the API.
        // See the DreamVacationAPI class for a method that can be used here
    	//JPanel panel = new JPanel();
    	comboBox = new JComboBox<Destination>();
    	for(Destination i : DreamVacationAPI.getDestinations()){
    		comboBox.addItem(i);
    	}
    	comboBox.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e){
    			updateDestination();
    		}
    	});

        // 1 point
        // Add an ActionListener to comboBox that calls updateDestination() (below) on an action


        // 1 point
        // Set the instance variable spinner to new JSpinner, then set its value to 10
        // Add a ChangeListener to spinner that calls updateDestination() (below) on a change.
        // Note: ChangeListener uses the same syntax as ActionListener but with a method named
        // stateChanged instead of actionPerformed
    	spinner = new JSpinner();
    	spinner.setValue(10);
    	spinner.addChangeListener(new ChangeListener(){
    		@Override
    		public void stateChanged(ChangeEvent e){
    			updateDestination();
    		}
    	});


        // 1 point
        // Set the instance variable mapLabel to a new JLabel
        // Set the instance variable chartArea to a new JTextArea with 15 rows and 40 columns
        // Set the instance variable descriptionArea to a new JTextArea with 20 rows and 40 columns
        // Set line wrap to true on both chatArea and descriptionArea by calling setLineWrap(boolean)
        // Set the instance variable tileServer equal to a new instance of your DreamVacationMap class
    	mapLabel = new JLabel();
    	chatArea = new JTextArea(15,40);
    	descriptionArea = new JTextArea(20,40);
    	
    	chatArea.setLineWrap(true);
    	descriptionArea.setLineWrap(true);
    	tileServer = new DreamVacationMap();


        updateDestination();
    }


    /**
     * Called when the destination or zoom level is changed. Updates all necessary GUI elements
     */
    private void updateDestination(){
        // Read comboBox and spinner (using getValue()) then update the following:
        // -[1 point] mapLabel to the map tile containing the selected destination at the zoom level from
        //  spinner from the tile server (These points are awarded even if the tile server isn't functional)
        // -[1 point] descriptionArea to the description of the selected location
    	
    	String marino = ((Destination)comboBox.getSelectedItem()).getDescription();
    	descriptionArea.setText(marino);
    	
    	Destination ricky = ((Destination)comboBox.getSelectedItem());
    	int williams = (int)spinner.getValue();
    	mapLabel.setIcon(tileServer.getTile(ricky, williams));
    	
    	 updateChat();
    }


    /**
     * Updates the chat area with the comments from the API for the currently selected destination
     */
    private void updateChat(){
        // 2 point
        // Read the comments from the API for the selected destination using a method from the DreamVacationAPI class
        // and set the text of chatArea to the comments separated by new lines.
    	//DreamVacationAPI.getDestinations();
    	
    	String j = "";
    	
    	String zachThomas = (((Destination)comboBox.getSelectedItem()).get_id());
    	
    	for(String x : (DreamVacationAPI.getComments(zachThomas))){
			j += x + "\n";
				
			}
    	chatArea.setText(j);

    }

    /**
     * @return A new panel containing only the mapLabel
     */
    public JPanel getMapPanel(){
        // 1 point
        // return a new panel containing only the mapLabel
    	JPanel marino = new JPanel();
    	marino.add(mapLabel);
        
        return marino;
    }

    /**
     * @return A new panel containing only the descriptionArea inside a JScrollPane
     */
    public JPanel getDescriptionPanel(){
        // 1 point
        // return a new panel containing only the descriptionArea inside a JScrollPane
    	JPanel wake = new JPanel();
    	wake.add(new JScrollPane(descriptionArea));

        return wake;
    }


    /**
     * @return A panel containing the inputs to select and add destinations
     */
    public JPanel getControlPanel(){
        // Creates the panel to be returned
        JPanel panel = new JPanel();

        // Set the layout manager to display all components in a single column
        // (This still doesn't look great.. feel free to adjust the layout)
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));


        // 1 point
        // Add a JLabel with the text "Destination:" to the panel
        // Add the instance variable comboBox to the panel
        // Add a JLabel with the text "Zoom:" to the panel
        // Add the instance variable spinner to the panel
        // Add a JLabel with the text "Name:" to the panel
        // Add a new JTextField to the panel that will be used to enter the name of a new location
        JLabel destinationLabel = new JLabel("Destination:");
        JLabel zoomLabel = new JLabel("Zoom:");
        JLabel nameLabel = new JLabel("Name:");
        
        JTextField locationNameTextField = new JTextField();
        
        panel.add(destinationLabel);
        panel.add(zoomLabel);
        panel.add(nameLabel);
        
        panel.add(comboBox);
        panel.add(spinner);
        panel.add(locationNameTextField);


        // 1 point
        // Add a JLabel with the text "Latitude:" to the panel
        // Add a new JTextField to the panel that will be used to enter the latitude of a new location
        // Add a JLabel with the text "Longitude:" to the panel
        // Add a new JTextField to the panel that will be used to enter the longitude of a new location
        JLabel latitudeLocation = new JLabel("Latitude:");
        JTextField latitudeTextField = new JTextField();
        JLabel longitudeLocation = new JLabel("Longitude:");
        JTextField longitudeTextField = new JTextField();
        
        panel.add(latitudeLocation);
        panel.add(latitudeTextField);
        panel.add(longitudeLocation);
        panel.add(longitudeTextField);


        // 1 point
        // Add a new JTextArea with 10 rows to the panel that will be used to enter the description of a new location
        // Set the line wrap of the text area to true
        // Note: with BoxLayout each component will be stretched to the panel width so there is no need to set the
        //       columns on the text area or fields on this panel
        JTextArea newLocationDescriptionTextArea = new JTextArea(10,1);
        newLocationDescriptionTextArea.setLineWrap(true);
        
        panel.add(newLocationDescriptionTextArea);


        // 2 points
        // Add a button to the panel with the text "Submit"
        // Add a new instance of SubmitLocationListener to this button as an ActionListener. Use the three text fields
        // and one text area as arguments in the constructor call (See below for SubmitLocationListener definition)
        JButton submitButton = new JButton();
        panel.add(submitButton);
        
        submitButton.addActionListener(new SubmitLocationListener(locationNameTextField, latitudeTextField, longitudeTextField, newLocationDescriptionTextArea){
        	/*@Override
        	public void actionPerformed(ActionEvent e){
        		//what the fuck do i put here
        	}*/
        });


        return panel;
    }

    /**
     * Listens for the user to submit a new destination, reads the form, and uploads the information to the API
     */
    public class SubmitLocationListener implements ActionListener{

        // Store references to the inputs in the form used to upload a new destination
        private JTextField nameField;
        private JTextField latitudeField;
        private JTextField longitudeField;
        private JTextArea descriptionArea;

        // The appropriate GUI components must be provided when instantiating a new SubmitLocationListener
        public SubmitLocationListener(JTextField nameField, JTextField latitudeField, JTextField longitudeField, JTextArea descriptionArea){
            this.nameField = nameField;
            this.latitudeField = latitudeField;
            this.longitudeField = longitudeField;
            this.descriptionArea = descriptionArea;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            // 2 points
            // Read the text fields and text area to create a new Destination with the user's information, then
            // upload this Destination to the API using a method provided in the DreamVacationAPI class
        	String a = nameField.getText();
        	Double b = new Double (latitudeField.getText());
        	Double c = new Double(longitudeField.getText());
        	String d = descriptionArea.getText();
        	
        	Destination destination = new Destination(a, d, b, c);
        	
        	DreamVacationAPI.addDestination(destination);
        	
        }
    }


    /**
     * @return a panel containing the chat portion of the app
     */
    public JPanel getChatPanel(){
        JPanel panel = new JPanel();

        // 1 point
        // Add a JLabel with the text "Name:" to the panel
        // Add a new JTextField with 15 columns to the panel that will be used to enter a username
        
        JLabel nameLabel = new JLabel("Name:");
        JTextField usernameTextField = new JTextField(15);
        
        panel.add(nameLabel);
        panel.add(usernameTextField);

        // 1 point
        // Add the member variable chatArea to the panel in a new JScrollPane
        // Add a new JTextField with 20 columns to the panel that will be used to enter chat messages
        
        panel.add(new JScrollPane(chatArea));
        JTextField chatMessageTextField = new JTextField(20);
        panel.add(chatMessageTextField);


        // 2 points
        // Add a KeyListener to this text field that will post its text as a comment under the selected destination
        // and call the "updateChat()" method.
        // There is a method to connect to the API in DreamVacationAPI. Be sure the pull the username from the
        // previous text field as well as the comment from the current text field.
        chatMessageTextField.addKeyListener(new KeyListener(){
        	@Override
        	public void keyTyped(KeyEvent e){
        		
        	}
        	
        	@Override
        	public void keyPressed(KeyEvent e){
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
        			
        			//DreamVacationAPI.getComments(DreamVacationAPI.getDestinations().toString());
        			updateChat();
        			
        			DreamVacationAPI.postComment(((Destination)comboBox.getSelectedItem()).get_id(), usernameTextField.getText(), chatMessageTextField.getText());
        			
        		}
        	}
        	
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        });


        // 1 point
        // Add a button to the panel with the text "Send"
        // Add an ActionListener to this button with the same functionality as the previous KeyListener
        JButton sendButton = new JButton("Send");
        panel.add(sendButton);
        sendButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		
        		DreamVacationAPI.postComment(((Destination)comboBox.getSelectedItem()).get_id(), usernameTextField.getText(), chatMessageTextField.getText());
        		
        		updateChat();
        	}
        });


        // 1 point
        // Add another button to the panel with the text "Refresh"
        // Add an ActionListener to this button that only calls "updateChat()"

        JButton refreshButton = new JButton("Refresh");
        panel.add(refreshButton);
        refreshButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		updateChat();
        	}
        });

        return panel;
    }

}
