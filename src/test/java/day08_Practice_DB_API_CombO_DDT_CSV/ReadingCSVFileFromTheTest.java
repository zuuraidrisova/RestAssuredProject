package day08_Practice_DB_API_CombO_DDT_CSV;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ReadingCSVFileFromTheTest {

    //this is to specify the test now has parameters
    @ParameterizedTest
    //this line is to specify we are using csv file as source
    //and provide the path for csv file under resource folder
    //and we skipped the first line since  it is the header and not actual data
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void simpleRead(int num1, int num2){

        System.out.println("num1 = " + num1);

        System.out.println("num2 = " + num2);

    }





// Please add another csv file called numbers.csv
// num1,num2, additionResult
// 5,4,9
// 4,7,11
// 3,8,11
// 6,10,16
/// Please add a @ParameterizedTest
/// specify the file source as numbers.csv
///  in the meantime add 3rd parameter to your test called int result
/// assert that num1 + num2  = result


    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv", numLinesToSkip = 1)
    public void testNumbers(int num1, int num2, int additionResult){


        assertThat(num1 + num2, equalTo(additionResult));

    }


    //what if i want some custom name
                //@ParameterizedTest(name = "some custom name here")
    //how do i refer the row number  in my csv file
                //u can use index in the name String to refer row number
    //how do i refer the column data in my display name
                //in the name String we can add {columnIndex}
                //{0} for first column
                //{1} for second column and so on

   // @ParameterizedTest(name = "B18 Learning Math {index} ")
    // WHERE DO I ADD the display name to start with ?
    // add it to @ParameterizedTest (name = " here")

    // we want display like this so we know exactly what we tested in each iteration
    // Row1 : 5+4=9
    // Row2 : 4+7=1

    //@ParameterizedTest(name = "Row{index} :   FirstColumn {0} +  Second Column{1} = ThirdColumn{2} ")
    @ParameterizedTest(name = "iteration {index} -> {0} + {1} = {2}")
    @CsvFileSource(resources = "/numbers.csv", numLinesToSkip = 1)
    public void testAddition(int num1, int num2, int result){

        assertThat(num1 + num2, equalTo(result));

    }






}
