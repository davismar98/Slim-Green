<?php
$spreadsheet_url="https://docs.google.com/spreadsheets/d/e/2PACX-1vTWYH82hW8wkP_U2YJmlsny70BJQVx8oTU4GsAARBlehn1XR5V2rdKyU6lAb2EdiO57e8i6f1pIgyUG/pub?gid=1758648512&single=true&output=csv";

if(!ini_set('default_socket_timeout', 15)) echo "<!-- unable to change socket timeout -->";

if (($handle = fopen($spreadsheet_url, "r")) !== FALSE) {
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
        $spreadsheet_data[] = $data;
    }
    fclose($handle);
}
else
    die("Problem reading csv");

echo '<pre>';
print_r($spreadsheet_data);
echo  '</pre>';

?>