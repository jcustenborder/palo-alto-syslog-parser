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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DataInterface {
  @JsonProperty
  String name;
  @JsonProperty
  String logType;
  @JsonProperty
  String doc;
  @JsonProperty
  List<Field> fields = new ArrayList<>();

  public String messageName() {
    return String.format("%s.%sMessage", this.getClass().getPackage().getName(), this.name);
  }

  public String parserTestName() {
    return String.format("%s.%sParserTest", this.getClass().getPackage().getName(), this.name);
  }

  public String parserName() {
    return String.format("%s.%sParser", this.getClass().getPackage().getName(), this.name);
  }

  public String immutableClassName() {
    return String.format("%s.Immutable%sMessage", this.getClass().getPackage().getName(), this.name);
  }

  public String immutableBuilderClassName() {
    return String.format("%s.Immutable%sMessage.Builder", this.getClass().getPackage().getName(), this.name);
  }

  public static class Field {
    @JsonProperty
    String name;
    @JsonProperty
    String methodName;
    @JsonProperty
    String doc;
    @JsonProperty
    Class<?> type = String.class;
    @JsonProperty
    boolean skip;
    @JsonProperty
    int index;

    public boolean shouldDefine() {
      return !this.skip && this.index > 3;
    }
  }
}
