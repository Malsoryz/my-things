program latihan9ikmal;

uses
  Vcl.Forms,
  Unit1 in 'Unit1.pas' {Flatihan9};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TFlatihan9, Flatihan9);
  Application.Run;
end.
