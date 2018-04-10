/**
 * Copyright © 2018 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.netty.paloalto;

import org.junit.jupiter.api.Assertions;

public class SystemLogParserTest
    extends PaloAltoParserTest<SystemLogMessage, SystemLogParser, SystemLogParserTestCase> {


  @Override
  protected SystemLogParser parser() {
    return new SystemLogParser();
  }

  @Override
  protected Class<SystemLogParserTestCase> testCaseClass() {
    return SystemLogParserTestCase.class;
  }

  @Override
  protected void assertMessage(SystemLogMessage expected, SystemLogMessage actual) {
    if (null != expected) {
      Assertions.assertNotNull(actual, "actual should not be null.");
    } else {
      Assertions.assertNull(actual, "actual should be null.");
    }
    Assertions.assertEquals(expected.subType(), actual.subType(), "subType() should match");
    Assertions.assertEquals(expected.generatedTime(), actual.generatedTime(), "generatedTime() should match");
    Assertions.assertEquals(expected.virtualSystem(), actual.virtualSystem(), "virtualSystem() should match");
    Assertions.assertEquals(expected.eventId(), actual.eventId(), "eventId() should match");
    Assertions.assertEquals(expected.object(), actual.object(), "object() should match");
    Assertions.assertEquals(expected.module(), actual.module(), "module() should match");
    Assertions.assertEquals(expected.severity(), actual.severity(), "severity() should match");
    Assertions.assertEquals(expected.description(), actual.description(), "description() should match");
    Assertions.assertEquals(expected.sequenceNumber(), actual.sequenceNumber(), "sequenceNumber() should match");
    Assertions.assertEquals(expected.actionFlags(), actual.actionFlags(), "actionFlags() should match");
    Assertions.assertEquals(expected.deviceGroupHierarchyLevel1(), actual.deviceGroupHierarchyLevel1(), "deviceGroupHierarchyLevel1() should match");
    Assertions.assertEquals(expected.deviceGroupHierarchyLevel2(), actual.deviceGroupHierarchyLevel2(), "deviceGroupHierarchyLevel2() should match");
    Assertions.assertEquals(expected.deviceGroupHierarchyLevel3(), actual.deviceGroupHierarchyLevel3(), "deviceGroupHierarchyLevel3() should match");
    Assertions.assertEquals(expected.deviceGroupHierarchyLevel4(), actual.deviceGroupHierarchyLevel4(), "deviceGroupHierarchyLevel4() should match");
    Assertions.assertEquals(expected.virtualSystemName(), actual.virtualSystemName(), "virtualSystemName() should match");
    Assertions.assertEquals(expected.deviceName(), actual.deviceName(), "deviceName() should match");
  }

}
