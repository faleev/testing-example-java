package com.example.wsdl.nde;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uk.neighbourhood.statistics.delivery.*;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static org.testng.Assert.assertEquals;

// http://neighbourhood.statistics.gov.uk/dissemination/LeadTableView.do?a=7&b=6275251&c=SO15+3JF&d=13&e=61&g=6401851&i=1001x1003x1032x1004&m=0&r=0&s=1404383141134&enc=1&dsFamilyId=2545
@Test(groups = "delivery")
public class NeighbourhoodStatisticsDeliveryTest {

    private static NeSSDelivery delivery;

    @BeforeClass
    public void setUp() {
        delivery = new Deli().getNeSSDeliveryBindingPort();
    }

    @Test(dependsOnGroups = { "discovery" })
    public void testThatGetTableReturnsCorrectData() throws Exception {
        String datasetItemValueExpected = "236882";
        String datasetTitleExpected = "Age by Single Year, 2011 (QS103EW)";
        String topicTitleExpected = "All Usual Residents";
        String topicDescriptionExpected = "All people usually resident in the area at the time of the 2011 Census.";

        String areaIdSouthampton = "6275251";
        String variableAllUsualResidents = "9447";

        ObjectFactory factory = new ObjectFactory();

        TablesElement areas = factory.createTablesElement();
        areas.setAreas(areaIdSouthampton);

        TablesElement variables = factory.createTablesElement();
        JAXBElement<String> variable = factory.createTablesElementVariables(variableAllUsualResidents);
        variables.setVariables(variable);

        TablesElement table = new TablesElement();
        table.setAreas(areaIdSouthampton);
        table.setVariables(variable);

        List<LGDxStructure.Dataset> dataset = delivery.getTables(table).getDatasets().getDataset();

        String datasetItemValue = dataset.get(0).getDatasetItems().getDatasetItem().get(0).getValue(); //"236882"
        String datasetTitle = dataset.get(0).getDatasetDetails().getDatasetMetadata().getTitle().get(0).getValue(); //"Age by Single Year, 2011 (QS103EW)"
        String topicTitle = dataset.get(0).getTopics().getTopic().get(0).getTopicMetadata().getTitle().get(0).getValue(); //"All Usual Residents"
        String topicDescription = dataset.get(0).getTopics().getTopic().get(0).getTopicMetadata().getDescription().get(0).getValue(); //"All people usually resident in the area at the time of the 2011 Census."

        assertEquals(datasetItemValue, datasetItemValueExpected);
        assertEquals(datasetTitle, datasetTitleExpected);
        assertEquals(topicTitle, topicTitleExpected);
        assertEquals(topicDescription, topicDescriptionExpected);
    }

}
