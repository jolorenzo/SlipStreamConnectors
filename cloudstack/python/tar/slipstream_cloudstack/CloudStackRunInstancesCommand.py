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

import warnings
warnings.filterwarnings("ignore", category=DeprecationWarning)

from slipstream.command.RunInstancesCommand import RunInstancesCommand
from slipstream_cloudstack.CloudStackCommand import CloudStackCommand
from slipstream_cloudstack.CloudStackClientCloud import CloudStackClientCloud
from slipstream_cloudstack.CloudStackAdvancedZoneClientCloud import CloudStackAdvancedZoneClientCloud


class CloudStackRunInstances(RunInstancesCommand, CloudStackCommand):

    NETWORKS_KEY = 'networks'
    ZONE_TYPE_KEY = 'zone-type'
    INSTANCE_TYPE_KEY = 'instance-type'
    SECURITY_GROUPS_KEY = 'security-groups'

    def get_connector_class(self):
        if self.get_option(self.ZONE_TYPE_KEY).lower() == 'advanced':
            return CloudStackAdvancedZoneClientCloud
        else:
            return CloudStackClientCloud

    def __init__(self):
        super(CloudStackRunInstances, self).__init__()

    def set_cloud_specific_options(self, parser):
        CloudStackCommand.set_cloud_specific_options(self, parser)

        self.parser.add_option('--' + self.ZONE_TYPE_KEY, dest=self.ZONE_TYPE_KEY,
                               help='Type of the zone (Basic [default], Advanced)',
                               default='Basic', metavar='ZONE-TYPE')

        self.parser.add_option('--' + self.INSTANCE_TYPE_KEY, dest=self.INSTANCE_TYPE_KEY,
                               help='Instance Type (Flavor)',
                               default=None, metavar='TYPE')

        self.parser.add_option('--' + self.SECURITY_GROUPS_KEY, dest=self.SECURITY_GROUPS_KEY,
                               help='Comma separated list of security groups',
                               default='', metavar='SECGROUPS')

        self.parser.add_option('--' + self.NETWORKS_KEY, dest=self.NETWORKS_KEY,
                               help='Networks (comma separated)',
                               default='', metavar='NETWORKS')

    def get_cloud_specific_node_inst_cloud_params(self):
        return {'security.groups': self.get_option(self.SECURITY_GROUPS_KEY),
                'instance.type': self.get_option(self.INSTANCE_TYPE_KEY),
                self.NETWORKS_KEY: self.get_option(self.NETWORKS_KEY)}

    def get_cloud_specific_mandatory_options(self):
        return CloudStackCommand.get_cloud_specific_mandatory_options(self) + \
               [self.INSTANCE_TYPE_KEY]

