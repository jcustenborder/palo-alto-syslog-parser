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

public class UserIdLogParserTest
    extends PaloAltoParserTest<UserIdLogMessage, UserIdLogParser, UserIdLogParserTestCase> {


  @Override
  protected UserIdLogParser parser() {
    return new UserIdLogParser();
  }

  @Override
  protected Class<UserIdLogParserTestCase> testCaseClass() {
    return UserIdLogParserTestCase.class;
  }

  @Override
  protected void assertMessage(UserIdLogMessage expected, UserIdLogMessage actual) {
    if (null != expected) {
      Assertions.assertNotNull(actual, "actual should not be null.");
    } else {
      Assertions.assertNull(actual, "actual should be null.");
    }
    Assertions.assertEquals(expected.threatContentType(), actual.threatContentType(), "threatContentType() should match");
    Assertions.assertEquals(expected.generatedTime(), actual.generatedTime(), "generatedTime() should match");
  }

}
