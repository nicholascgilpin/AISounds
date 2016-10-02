

public class AISoundsMain {
	public static void main(String[] args){
		
		MidiConverter midi = new MidiConverter();
		//Phrase phrase = midi.music_import("src/audio/Smbtheme.mid");
		//midi.music_export(phrase, "src/audio/New.mid");
		midi.music_play("src/audio/Smbtheme.mid");
	}
}
