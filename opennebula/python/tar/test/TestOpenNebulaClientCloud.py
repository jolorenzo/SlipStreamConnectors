#!/usr/bin/env python
"""
 SlipStream Client
 =====
 Copyright (C) 2014 SixSq Sarl (sixsq.com)
 =====
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
"""

import os
import unittest
from slipstream.ConfigHolder import ConfigHolder
from slipstream_opennebula.OpenNebulaClientCloud import OpenNebulaClientCloud


class TestOpenNebulaClientCloud(unittest.TestCase):

    connector_instance_name = 'opennebula'

    def setUp(self):
        os.environ['SLIPSTREAM_CONNECTOR_INSTANCE'] = self.connector_instance_name

    def tearDown(self):
        pass

    def test_init(self):
        OpenNebulaClientCloud(ConfigHolder(context={'foo': 'bar'}))


if __name__ == '__main__':
    unittest.main()
