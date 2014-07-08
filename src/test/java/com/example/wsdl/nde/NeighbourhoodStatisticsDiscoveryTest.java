package com.example.wsdl.nde;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uk.neighbourhood.statistics.discovery.*;

import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.util.List;

import static org.testng.Assert.assertEquals;

// Details on http://neighbourhood.statistics.gov.uk/dissemination/
public class NeighbourhoodStatisticsDiscoveryTest {

    private static NeSSDiscovery discovery;

    @BeforeClass
    public void setUp() {
        discovery = new Disco().getNeSSDiscoveryBindingPort();
    }

    @Test
    public void testThatSubjectHasCorrectId() throws Exception {
        BigDecimal subjectId = new BigDecimal(0);
        BigDecimal subjectIdExpected = new BigDecimal(58);
        String subjectName = "Census";

        SubjectsRequest subjectRequest = new SubjectsRequest();
        List<Subject> listOfSubjects = discovery.getSubjects(subjectRequest).getSubjects().getSubject();

        for (Subject s : listOfSubjects) {
            if (s.getName().equals(subjectName)) {
                subjectId = s.getSubjectId();
            }
        }

        assertEquals(subjectId, subjectIdExpected);
    }

    @Test
    public void testThatDatasetHasCorrectId() throws Exception {
        BigDecimal subjectId = new BigDecimal(58);
        BigDecimal datasetId = new BigDecimal(0);
        BigDecimal datasetIdExpected = new BigDecimal(2545);
        String datasetName = "Age by Single Year";

        DatasetsRequest datasetsRequest = new DatasetsRequest();
        datasetsRequest.setSubjectId(subjectId);
        List<DSFamily> listOfDSFamilies = discovery.getDatasets(datasetsRequest).getDSFamilies().getDSFamily();

        for (DSFamily f : listOfDSFamilies) {
            if (f.getName().contains(datasetName)) {
                datasetId = f.getDSFamilyId();
            }
        }

        assertEquals(datasetId, datasetIdExpected);
    }

    @Test
    public void testThatVariableHasCorrectId() throws Exception {
        BigDecimal datasetId = new BigDecimal(2545);
        BigDecimal variableId = new BigDecimal(0);
        BigDecimal variableIdExpected = new BigDecimal(9447);
        String variableName = "All Usual Residents";

        VariablesRequest variablesRequest = new VariablesRequest();
        variablesRequest.setDSFamilyId(datasetId);
        List<VarFamily> listOfVarFamily = discovery.getVariables(variablesRequest).getVarFamilies().getVarFamily();

        for (VarFamily v : listOfVarFamily) {
            if (v.getName().equals(variableName)) {
                variableId = v.getVarFamilyId();
            }
        }

        assertEquals(variableId, variableIdExpected);
    }

    @Test
    public void testThatSpecifiedAreaHasCorrectId() throws Exception {
        String area = "Southampton";
        String hierarchy = "26";
        String level = "13";
        BigDecimal areaId = new BigDecimal(0);
        BigDecimal areaIdExpected = new BigDecimal(6275251);

        ObjectFactory factory = new ObjectFactory();

        JAXBElement<String> areaName = factory.createFindAreasRequestAreaNamePart(area);
        JAXBElement<BigDecimal> hierarchyId = factory.createFindAreasRequestHierarchyId(new BigDecimal(hierarchy));
        JAXBElement<BigDecimal> levelId = factory.createFindAreasRequestLevelTypeId(new BigDecimal(level));

        FindAreasRequest findAreasRequest = new FindAreasRequest();

        findAreasRequest.setAreaNamePart(areaName);
        findAreasRequest.setHierarchyId(hierarchyId);
        findAreasRequest.setLevelTypeId(levelId);

        List<AreaFallsWithin> listOfAreas = discovery.findAreas(findAreasRequest).getAreaFallsWithins().getAreaFallsWithin();

        for (AreaFallsWithin listOfArea : listOfAreas) {
            if (listOfArea.getArea().getName().equals(area)) {
                areaId = listOfArea.getArea().getAreaId();
            }
        }

        assertEquals(areaId, areaIdExpected);
    }
}
