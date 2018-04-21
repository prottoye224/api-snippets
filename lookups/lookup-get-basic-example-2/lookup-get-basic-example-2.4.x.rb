# Get twilio-ruby from twilio.com/docs/ruby/install
require 'twilio-ruby'

# Get your Account Sid and Auth Token from twilio.com/user/account
account_sid = 'ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'
auth_token = 'your_auth_token'
client = Twilio::REST::LookupsClient.new(account_sid, auth_token)

number = client.phone_numbers
               .get('(510) 867-5310', country_code: 'US', type: 'carrier')

puts number.carrier['type']
puts number.carrier['name']
