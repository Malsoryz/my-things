unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.ComCtrls, Vcl.StdCtrls;

type
  TFLatihan3 = class(TForm)
    drh: TLabel;
    nama: TLabel;
    Edit1: TEdit;
    ttl: TLabel;
    Edit2: TEdit;
    DateTimePicker1: TDateTimePicker;
    Jenisk: TLabel;
    pria: TRadioButton;
    wanita: TRadioButton;
    agama: TLabel;
    agamabox: TComboBox;
    pekerjaan: TLabel;
    alamat: TLabel;
    kodepos: TLabel;
    kodeposn: TEdit;
    save: TButton;
    reset: TButton;
    close: TButton;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FLatihan3: TFLatihan3;

implementation

{$R *.dfm}

end.
