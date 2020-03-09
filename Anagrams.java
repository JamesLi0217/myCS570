import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Puzhuo Li 
 * compute the list of words in a given dictionary that has
 *         the most number of anagrams
 *
 */
public class Anagrams
{
	/**
	 * primes: reduce hash collision letterTable: a:2, b:3.... anagramTable: key is
	 * hashCode, value is list of words
	 */
	final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
			89, 97, 101 };
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	/**
	 * Constructor Initial letterTable and anagramTable
	 */
	public Anagrams()
	{
		buildLetterTable(); // invoked by the constructor
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}

	/**
	 * build buildLetterTable when initial Anagrams
	 */
	private void buildLetterTable()
	{
		Character[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		letterTable = new HashMap<Character, Integer>();
		for (int i = 0; i < 26; i++)
		{
			letterTable.put(letters[i], primes[i]);
		}
	}

	/**
	 * add String s into hashMap
	 * 
	 * @param s
	 */
	private void addWord(String s)
	{
		Long hashCode = myHashCode(s);
		ArrayList<String> values = anagramTable.get(hashCode);
		if (values == null)
		{
			values = new ArrayList<String>();
			anagramTable.put(hashCode, values);
		}
		values.add(s);
	}

	/**
	 * 
	 * @param s
	 * @return hashCode, type is long
	 */
	private Long myHashCode(String s)
	{

//		char[] array = s.toCharArray();
		Long hashCode = 1L;
		for (int i = 0; i < s.length(); i++)
		{
			hashCode *= letterTable.get(s.charAt(i));
		}
		return hashCode;

	}

	/**
	 * read file line by line In this file, one word in every line
	 * 
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException
	{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)
		{
			this.addWord(strLine);
		}
		br.close();

	}

	/**
	 * Find entity with max number
	 * 
	 * @return
	 */
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries()
	{
		ArrayList<Map.Entry<Long, ArrayList<String>>> res = new ArrayList<>();
		int max = 0;
		int size = 0;
		for (Map.Entry<Long, ArrayList<String>> wordEntry : anagramTable.entrySet())
		{
			size = wordEntry.getValue().size();
			if (size > max)
			{
				res.clear(); // clear entry with lower key
				res.add(wordEntry);
				max = size;
			} else if (size == max)
			{
				res.add(wordEntry);
			}
		}
		return res;

	}

//	@Test
//	public void anagramsTest() {
//		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
//	}

	public static void main(String[] args)
	{
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try
		{
			a.processFile("words_alpha.txt");
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		System.out.println(maxEntries);
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);

		// if max anagrams is not only one, for loop
		// same time, same number, but different key and value
		System.out.println("Elapsed Time: " + seconds);
		for (Map.Entry<Long, ArrayList<String>> wordEntry : maxEntries)
		{
			System.out.println("Key of max anagrams: " + wordEntry.getKey());
			System.out.println("List of max anagrams: " + wordEntry.getValue());
		}
		System.out.println("Length of list of max anagrams: " + maxEntries.get(0).getValue().size());

		// Test....
		Map<Long, ArrayList<String>> expect = new HashMap<Long, ArrayList<String>>();
		String[] value = new String[] { "alerts", "alters", "artels", "estral", "laster", "lastre", "rastle", "ratels",
				"relast", "resalt", "salter", "slater", "staler", "stelar", "talers" };
		expect.put((long) 236204078, new ArrayList<String>(Arrays.asList(value)));
		assertEquals(maxEntries.toString(), expect.entrySet().toString());

	}

}
